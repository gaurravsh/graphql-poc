package com.shaga.graphql.service;

import com.shaga.graphql.model.Book;
import com.shaga.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepo;

    @Override
    public Book get(DataFetchingEnvironment environment){
        String isin = environment.getArgument("isin");
        return bookRepo.findBook(isin);
    }
}
