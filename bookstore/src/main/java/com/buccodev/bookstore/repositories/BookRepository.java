package com.buccodev.bookstore.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buccodev.bookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID>{

}
