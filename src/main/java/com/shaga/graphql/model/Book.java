package com.shaga.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String isin;
    private String title;
    private String publisher;
    private String[] author;
    private String publishingDate;
}
