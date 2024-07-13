package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.dto.AuthorDTO;
import com.buccodev.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorResource {

    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){

        List<AuthorDTO> authors = service.findAllAuthors();

        return ResponseEntity.ok().body(authors);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable UUID id){

        Author author = service.findAuthorById(id);

        AuthorDTO authorDTO = AuthorDTO.fromAuthor(author);

        return ResponseEntity.ok().body(authorDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorDTO> findAuthorByName(@PathVariable String name){

        Author author = service.findByNameAuthor(name);

        AuthorDTO authorDTO = AuthorDTO.fromAuthor(author);

        return ResponseEntity.ok().body(authorDTO);
    }

    @PostMapping
    public ResponseEntity<Void> saveAuthor(@RequestBody AuthorDTO authorDTO){

        service.saveAuthor(authorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable UUID id, @RequestBody AuthorDTO authorDTO){

       service.updateAuthor(id, AuthorDTO.toAuthorFromDto(authorDTO));

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id){

        service.deleteAuthorById(id);

        return ResponseEntity.noContent().build();
    }



}
