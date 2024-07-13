package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.entity.dto.BookDTO;
import com.buccodev.bookstore.services.AuthorService;
import com.buccodev.bookstore.services.BookService;
import com.buccodev.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/book")
public class BookResourse {

    @Autowired
    private BookService service;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        List<BookDTO> books = service.findAllBooks();

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable UUID id){

        Book book = service.findBookById(id);

        BookDTO bookDTO = BookDTO.fromBook(book);

        return  ResponseEntity.ok().body(bookDTO);

    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookDTO> findByTitle(@PathVariable String title){

        Book book = service.findBookByTitle(title);

        BookDTO bookDTO = BookDTO.fromBook(book);

        return ResponseEntity.ok().body(bookDTO);

    }


    @PostMapping
    public ResponseEntity<Void> saveBook(@RequestBody BookDTO bookDTO) {

        service.saveBook(bookDTO);



        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable UUID id, @RequestBody BookDTO bookDTO) {

        service.updateBook(id, bookDTO);

        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id){

        service.deleteBookById(id);

        return ResponseEntity.ok().build();
    }

}
