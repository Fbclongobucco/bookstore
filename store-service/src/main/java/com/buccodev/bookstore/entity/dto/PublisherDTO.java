package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Publisher;

public record PublisherDTO(String name, String country) {

    public static PublisherDTO fromPublisher(Publisher publisher){

        return new PublisherDTO(
                publisher.getName(),
                publisher.getCountry()
        );
    }


    public static Publisher toPublisherFromDTO(PublisherDTO publisherDTO){

        return new Publisher(
                null,
                publisherDTO.name(),
                publisherDTO.country()
        );

    }
}
