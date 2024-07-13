package com.buccodev.bookstore.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buccodev.bookstore.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID>{

    Optional<Author> findByName(String name);

    boolean existsByName(String name);

}
