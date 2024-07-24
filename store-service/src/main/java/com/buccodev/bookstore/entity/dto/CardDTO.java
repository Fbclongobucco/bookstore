package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Card;
import com.buccodev.bookstore.entity.Client;
import com.buccodev.bookstore.entity.enuns.FlagCard;
import com.buccodev.bookstore.entity.enuns.PayMethod;

import java.time.LocalDate;

public record CardDTO(Client client, String numCard, LocalDate validity, String cvc, PayMethod payMethod,
                      FlagCard flag) {

    public static CardDTO fromCard(Card card){
        return new CardDTO(
                card.getClient(),
                card.getNumCard(),
                card.getValidity(),
                card.getCvc(),
                card.getPayMethod(),
                card.getFlag()

        );
    }

    public static Card toCardFromDTO(CardDTO cardDTO){
        return new Card(
                null,
                cardDTO.client(),
                cardDTO.numCard(),
                cardDTO.validity(),
                cardDTO.numCard(),
                cardDTO.payMethod(),
                cardDTO.flag()
        );
    }

}
