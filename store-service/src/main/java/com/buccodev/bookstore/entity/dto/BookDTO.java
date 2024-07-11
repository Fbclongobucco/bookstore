package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.entity.enuns.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record BookDTO(String title, LocalDate localDate, BigDecimal price, Category category, Integer quantityStock, Publisher publisher, Set<Author> authors) {



    public Book toBook() {
        Book book = new Book(
                null,
                this.title(),
                this.localDate(),
                this.price(),
                this.category(),
                this.publisher()
        );


        book.getAuthors().addAll(this.authors());

        return book;

    }

    public static BookDTO fromBook(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getDate(),
                book.getPrice(),
                book.getCategory(),
                book.getQuantityStock(),
                book.getPublisher(),
                book.getAuthors()
        );
    }

    public static Book toBookFromDto(BookDTO bookDto) {
        return new Book(
                null,
                bookDto.title(),
                bookDto.localDate(),
                bookDto.price(),
                bookDto.category(),
                null
        );
    }


}
