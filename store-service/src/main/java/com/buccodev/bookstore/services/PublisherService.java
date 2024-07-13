package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.repositories.PublisherRepository;
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
public class PublisherService {

    @Autowired
    private PublisherRepository repository;


    public UUID savePublisher(Publisher publisher){

        try{

            return repository.save(publisher).getId();

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Publisher findPublisherById(UUID id){

        var publisher = repository.findById(id);

        return publisher.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public Publisher findByName(String title){

        var publisher = repository.findByName(title);

        return publisher.orElseThrow(()-> new ResourceNotFoundException(title));

    }

    public List<Publisher> findAllPublisher(){

        return repository.findAll();
    }

    public void deletePublisherById(UUID id){

        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public Publisher updatePublisher(UUID id, Publisher newPublisher){

            Publisher publisher = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

            updateData(publisher, newPublisher);

            return repository.save(publisher);


    }

    private void updateData(Publisher oldPublisher, Publisher newPublisher) {

        oldPublisher.setCountry(newPublisher.getCountry());

        oldPublisher.setName(newPublisher.getName());

    }

}
