package com.buccodev.bookstore.repositories;

import com.buccodev.bookstore.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}
