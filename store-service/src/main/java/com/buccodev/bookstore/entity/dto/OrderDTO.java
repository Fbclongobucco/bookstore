package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.entity.enuns.PaymentMethod;

import java.time.Instant;
import java.util.UUID;

public record OrderDTO(Instant instant, PaymentMethod paymentMethod, UUID idClient) {

    public static OrderDTO fromOrder(Order order){
        return new OrderDTO(
                order.getInstant(),
                order.getMethodPayment(),
                order.getClient().getId()
        );
    }

    public static Order toOrderFromDTO(OrderDTO orderDTO){


      return new Order(
              null,
              orderDTO.instant(),
              orderDTO.paymentMethod(),
              null
            );

    }

}
