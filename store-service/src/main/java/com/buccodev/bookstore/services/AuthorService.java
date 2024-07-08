package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.repositories.AuthorRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public UUID saveAuthor(Author author){

        try{

            UUID uuid = repository.save(author).getId();

            return uuid;

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Author findAddressById(UUID id){

        Optional<Author> author = repository.findById(id);

        return author.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public List<Author> findAllAuthors(){

        return repository.findAll();
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

    public Author updateAuthor(UUID id, Author newAuthor){

        try{

            Author author = repository.findById(id).get();

            updateData(author, newAuthor);

            return repository.save(author);

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
