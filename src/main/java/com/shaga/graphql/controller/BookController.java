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

    @PostMapping("/book")
    public ResponseEntity<ExecutionResult> getAllBooks(@RequestBody String query){
        System.out.printf("Query is : %s%n",query);
        ExecutionResult execute = graphqlService.getGraphQL().execute(query);
        System.out.println("error is  " + execute.getErrors());
        return new ResponseEntity<>(execute, execute.getErrors()!=null && execute.getErrors().size()>0 ? HttpStatus.BAD_REQUEST: HttpStatus.OK);
    }
}
