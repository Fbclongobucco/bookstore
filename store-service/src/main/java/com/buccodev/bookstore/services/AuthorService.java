package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.entity.dto.AuthorDTO;
import com.buccodev.bookstore.repositories.AuthorRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public UUID saveAuthor(AuthorDTO authorDTO){

        try{

            return repository.save(AuthorDTO.toAuthorFromDto(authorDTO)).getId();

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Author findAuthorById(UUID id){

        var author = repository.findById(id);

        return author.orElseThrow(()-> new ResourceNotFoundException("Resource "+id+" not found!"));

    }

    public List<AuthorDTO> findAllAuthors(){

        List<Author> authors = repository.findAll();

        return authors.stream().map(AuthorDTO::fromAuthor).toList();
    }

    public void deleteAuthorById(UUID id){

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public Author findByNameAuthor(String name){

        return repository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Resource "+name+" Not found"));

    }

    public Author updateAuthor(UUID id, Author newAuthor){

        try{

            Optional<Author> author = repository.findById(id);

            updateData(author.get(), newAuthor);

            return repository.save(author.get());

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Author oldAuthor, Author newAuthor){

        oldAuthor.setName(newAuthor.getName());
        oldAuthor.setCountry(newAuthor.getCountry());
        oldAuthor.setBirthday(newAuthor.getBirthday());

    }

}
