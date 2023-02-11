package com.shaga.graphql.repository;

import com.shaga.graphql.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> bookList;

    public List<Book> findAll(){
        return bookList;
    }

    public Book findBook(String isin){
        return bookList.stream().filter(x -> x.getIsin().equalsIgnoreCase(isin)).findFirst().orElse(new Book());
    }

    public void setBookList(List<Book> bookList){
        this.bookList=bookList;
    }
}
