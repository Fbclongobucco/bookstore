package com.buccodev.bookstore.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.buccodev.bookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID>{

    Optional<Book> findByTitle(String title);

    Page<Book> findAll(Pageable pageable);

}
