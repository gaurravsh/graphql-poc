package com.shaga.graphql.service;

import com.shaga.graphql.model.Book;
import com.shaga.graphql.repository.BookRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class GraphqlService {

    private GraphQL graphql;

    @Value("classpath:books.graphql")
    Resource resource;


    @Autowired
    BookRepository bookRepo;

    @Autowired
    AllBookDataFetcher allBookDataFetcher;

    @Autowired
    BookDataFetcher bookDataFetcher;
    @PostConstruct
    private void loadSchema(){
        loadBookDateInRepo();
        try {
            File schemaFile = resource.getFile();
            TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
            RuntimeWiring wiring = buildRuntimeWiring();
            GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
            graphql = GraphQL.newGraphQL(schema).build();
        }
        catch(IOException e){
            System.out.printf("Resource %s not found %n",resource.getFilename());
            e.printStackTrace();
        }
    }

    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring().type("Query",typewiring ->
            typewiring
                    .dataFetcher("allBooks", allBookDataFetcher)
                    .dataFetcher("book",bookDataFetcher)
        ).build();
    }

    public GraphQL getGraphQL(){
        return graphql;
    }

    private void loadBookDateInRepo(){
        Book book1 = new Book("12344","Palace of Illusion","Penguin",new String [] {"Chitra Bannerjee"},"2008-01-01");
        Book book2 = new Book("3456","Alchemist","Jaico",new String [] {"Paulo Coelho"},"1988-01-01");
        Book book3 = new Book("4567","The Zaheer","Jaico",new String [] {"Paulo Coelho"},"2005-01-01");
        List<Book> bookList = new LinkedList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookRepo.setBookList(bookList);
    }
}
