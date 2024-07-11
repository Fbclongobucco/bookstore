package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorResource {

    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){

        List<Author> authors = service.findAllAuthors();

        return ResponseEntity.ok().body(authors);

    }


}
