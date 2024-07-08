package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.repositories.PublisherRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherService {

    private PublisherRepository repository;


    public UUID savePublisher(Publisher publisher){

        try{

            UUID uuid = repository.save(publisher).getId();

            return uuid;

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Publisher findPublisherById(UUID id){

        Optional<Publisher> order = repository.findById(id);

        return order.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public List<Publisher> findAllClient(){

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

    public Publisher updateOrder(UUID id, Publisher newPublisher){

        try{
            Publisher publisher = repository.findById(id).get();

            updateData(publisher, newPublisher);

            return repository.save(publisher);

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Publisher oldPublisher, Publisher newPublisher) {

        oldPublisher.setCountry(newPublisher.getCountry());
        oldPublisher.setName(newPublisher.getName());;

    }

}
