package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Author;

import java.time.LocalDate;

public record AuthorDTO(String name, String country, LocalDate birthday) {

    public static AuthorDTO fromAuthor(Author author) {
        return new AuthorDTO(
                author.getName(),
                author.getCountry(),
                author.getBirthday()
        );
    }

    public static Author toAuthorFromDto(AuthorDTO authorDTO){
        return new Author(null,
                authorDTO.name(),
                authorDTO.country(),
                authorDTO.birthday()
        );
    }
}
