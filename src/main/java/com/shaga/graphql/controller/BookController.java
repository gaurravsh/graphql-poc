package com.shaga.graphql.controller;

import com.shaga.graphql.service.GraphqlService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    GraphqlService graphqlService;

    @PostMapping("/allBooks")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query){
        System.out.printf("Query is : %s%n",query);
        ExecutionResult execute = graphqlService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
