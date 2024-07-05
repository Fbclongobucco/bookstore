package com.buccodev.bookstore.repositories;

import com.buccodev.bookstore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
