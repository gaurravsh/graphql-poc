package com.shaga.graphql.repository;

import com.shaga.graphql.model.Book;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> bookList;

    @PostConstruct
    private void loadBookDateInRepo(){
        Book book1 = new Book("12344","Palace of Illusion","Penguin",new String [] {"Chitra Bannerjee"},"2008-01-01");
        Book book2 = new Book("3456","Alchemist","Jaico",new String [] {"Paulo Coelho"},"1988-01-01");
        Book book3 = new Book("4567","The Zaheer","Jaico",new String [] {"Paulo Coelho"},"2005-01-01");
        bookList = new LinkedList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
    }

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
