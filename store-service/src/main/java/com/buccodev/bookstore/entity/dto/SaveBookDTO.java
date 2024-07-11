package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Publisher;

import java.util.List;

public record SaveBookDTO(BookDTO bookDTO, Publisher publisher, List<Author> authors) {
}
