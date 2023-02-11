package com.shaga.graphql.service;

import com.shaga.graphql.model.Book;
import com.shaga.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>> {
    @Autowired
    BookRepository bookRepo;

    @Override
    public List<Book> get(DataFetchingEnvironment environment) {
        return bookRepo.findAll();
    }
}
