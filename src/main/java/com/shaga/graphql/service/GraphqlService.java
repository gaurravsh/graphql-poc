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

}
