package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.entity.dto.BookDTO;
import com.buccodev.bookstore.repositories.AuthorRepository;
import com.buccodev.bookstore.repositories.BookRepository;
import com.buccodev.bookstore.repositories.PublisherRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public UUID saveBook(BookDTO bookDTO) {

        Book book = new Book();
        book.setQuantityStock(bookDTO.quantityStock());
        book.setCategory(bookDTO.category());
        book.setPrice(bookDTO.price());
        book.setDate(bookDTO.localDate());
        book.setTitle(bookDTO.title());

        List<Author> authors = bookDTO.authors().stream().toList();

        for (Author author : authors) {

            var a = authorRepository.existsByName(author.getName());

            if (a) {

                book.getAuthors().add(authorRepository.findByName(author.getName()).get());

                author.getBooks().add(book);

            } else {
                var salved = authorRepository.save(author);

                book.getAuthors().add(salved);

                author.getBooks().add(book);

            }


        }

        var test = publisherRepository.existsByName(bookDTO.publisher().getName());

        Publisher publisher = null;

        if (test) {
            publisher = publisherRepository.findByName(bookDTO.publisher().getName()).get();

            publisher.getBooks().add(book);

        } else {

            publisher = publisherRepository.save(bookDTO.publisher());

            publisher.getBooks().add(book);
        }


        book.setPublisher(publisher);


        return repository.save(book).getId();
    }

    public Book findBookById(UUID id) {

        var book = repository.findById(id);

        return book.orElseThrow(() -> new ResourceNotFoundException(id));

    }

    public Book findBookByTitle(String title) {

        var book = repository.findByTitle(title);

        return book.orElseThrow(() -> new ResourceNotFoundException(title));
    }

    public List<BookDTO> findAllBooks() {

        List<Book> books = repository.findAll();

        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(book.getTitle(), book.getDate(), book.getPrice(),
                    book.getCategory(), book.getQuantityStock(), book.getAuthors(), book.getPublisher());

            bookDTOS.add(bookDTO);
        }

        return bookDTOS;
    }

    public void deleteBookById(UUID id) {

        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {

            throw new DataBaseException(e.getMessage());
        }

    }

    public void updateBook(UUID id, BookDTO newBook) {

        try {

            var book = repository.findById(id).get();

            updateData(book, newBook);

            repository.save(book);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Book oldBook, BookDTO newBookDTO) {

        oldBook.setCategory(newBookDTO.category());
        oldBook.setDate(newBookDTO.localDate());
        oldBook.setPrice(newBookDTO.price());
        oldBook.setPublisher(newBookDTO.publisher());
        oldBook.setTitle(newBookDTO.title());

    }

}
