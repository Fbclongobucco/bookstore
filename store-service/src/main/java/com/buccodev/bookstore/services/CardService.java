package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Card;
import com.buccodev.bookstore.repositories.CardRepository;
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
public class CardService {

    private CardRepository repository;


    public UUID saveCard(Card card){

        try{

            UUID uuid = repository.save(card).getId();

            return uuid;

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }


    public Card findCardById(UUID id){

        Optional<Card> book = repository.findById(id);

        return book.orElseThrow(()-> new ResourceNotFoundException(id));

    }


    public List<Card> findAllCards(){

        return repository.findAll();
    }

    public void deleteCardById(UUID id){

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public Card updateCard(UUID id, Card newCard){

        try{

            Card card = repository.findById(id).get();

            updateData(card, newCard);

            return repository.save(card);

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Card oldCard, Card newCard) {

        oldCard.setPayMethod(newCard.getPayMethod());
        oldCard.setFlag(newCard.getFlag());
        oldCard.setNumCard(newCard.getNumCard());
        oldCard.setCvc(newCard.getCvc());
        oldCard.setValidity(newCard.getValidity());


    }

}
