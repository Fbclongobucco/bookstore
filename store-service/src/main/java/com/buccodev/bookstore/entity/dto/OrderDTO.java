package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.entity.OrderItem;
import com.buccodev.bookstore.entity.enuns.PaymentMethod;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record OrderDTO(Instant instant, PaymentMethod paymentMethod, UUID idClient, List<OrdemItemDTO> ordemItemDTOS) {

    public static OrderDTO fromOrder(Order order){
        return new OrderDTO(
                order.getInstant(),
                order.getMethodPayment(),
                order.getClient().getId(),
                order.getItens().stream().map(orderItem ->
                        new OrdemItemDTO(order.getClient().getId(), orderItem.getQuantity())).toList()
        );
    }

    public static Order toOrderFromDTO(OrderDTO orderDTO){

        Order order = new Order();

        order.setInstant(orderDTO.instant());
        order.setMethodPayment(orderDTO.paymentMethod());
        return order;
    }

}
