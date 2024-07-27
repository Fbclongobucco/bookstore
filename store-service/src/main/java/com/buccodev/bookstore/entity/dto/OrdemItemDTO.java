package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.OrderItem;

import java.util.UUID;

public record OrdemItemDTO(UUID idBook, Integer quantity) {

    public static OrdemItemDTO fromOrderItem(OrderItem orderItem){

        return new OrdemItemDTO(
                orderItem.getOrder().getId(),
                orderItem.getQuantity()
        );
    }

    public static OrderItem toOrderItemFromDTO(OrdemItemDTO ordemItemDTO){
        return new OrderItem(
                null,
                null,
                ordemItemDTO.quantity()
        );
    }

}
