package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.repositories.BookRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;


    public UUID saveBook(Book book){

        try{

            UUID uuid = repository.save(book).getId();

            return uuid;

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Book findBookById(UUID id){

        Optional<Book> book = repository.findById(id);

        return book.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public List<Book> findAllAuthors(){

        return repository.findAll();
    }

    public void deleteBookById(UUID id){

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public Book updateBook(UUID id, Book newBook){

        try{

            Book book = repository.findById(id).get();

            updateData(book, newBook);

            return repository.save(book);

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Book oldBook, Book newBook) {

        oldBook.setCategory(newBook.getCategory());
        oldBook.setDate(newBook.getDate());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setPublisher(newBook.getPublisher());
        oldBook.setTitle(newBook.getTitle());

    }


}
