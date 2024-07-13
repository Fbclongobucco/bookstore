package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.entity.enuns.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record BookDTO(String title, LocalDate localDate, BigDecimal price, Category category, Integer quantityStock, Set<Author> authors, Publisher publisher) {




    public static BookDTO fromBook(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getDate(),
                book.getPrice(),
                book.getCategory(),
                book.getQuantityStock(),
                book.getAuthors(),
                book.getPublisher()
        );
    }

    public static Book toBookFromDto(BookDTO bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.title());
        book.setDate(bookDto.localDate());
        book.setPrice(bookDto.price());
        book.setQuantityStock(bookDto.quantityStock());
        book.setCategory(bookDto.category());
        book.setPublisher(bookDto.publisher());
        book.getAuthors().addAll(bookDto.authors());
        return book;
    }


}
