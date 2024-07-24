package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.dto.CardDTO;
import com.buccodev.bookstore.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/card")
public class CardResource {

    @Autowired
    public CardService service;

    @GetMapping
    public ResponseEntity<List<CardDTO>> getAllCard(){

        List<CardDTO> cardDTOS = service.findAllCards();

        return ResponseEntity.ok().body(cardDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findCardById(@PathVariable UUID id){

        CardDTO cardDTO = service.findCardById(id);

        return ResponseEntity.ok().body(cardDTO);
    }

    @PostMapping
    public ResponseEntity<Void> saveCard(@RequestBody CardDTO cardDTO){

        service.saveCard(cardDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCard(@PathVariable UUID id, @RequestBody CardDTO cardDTO){

        service.updateCard(id, cardDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID id){

        service.deleteCardById(id);

        return ResponseEntity.ok().build();
    }
}
