package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Card;
import com.buccodev.bookstore.entity.dto.CardDTO;
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


    public UUID saveCard(CardDTO cardDTO){

        try{

            Card card = CardDTO.toCardFromDTO(cardDTO);

            return repository.save(card).getId();

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }


    public CardDTO findCardById(UUID id){

        Card card = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

        return CardDTO.fromCard(card);

    }


    public List<CardDTO> findAllCards(){

        List<Card> cards = repository.findAll();

        return cards.stream().map(CardDTO::fromCard).toList();
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

    public void updateCard(UUID id, CardDTO newCardDTO){

        try{

            Card newCard = CardDTO.toCardFromDTO(newCardDTO);

            Card card = repository.findById(id).get();

            updateData(card, newCard);

           repository.save(card);

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
