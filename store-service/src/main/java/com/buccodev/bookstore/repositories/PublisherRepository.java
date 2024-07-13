package com.buccodev.bookstore.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buccodev.bookstore.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, UUID>{

    Optional<Publisher> findByName(String name);

    boolean existsByName(String name);

}
