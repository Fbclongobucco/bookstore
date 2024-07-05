package com.buccodev.bookstore.repositories;

import com.buccodev.bookstore.entity.OrderItem;
import com.buccodev.bookstore.entity.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
