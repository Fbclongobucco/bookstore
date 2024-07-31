package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.entity.dto.OrdemItemDTO;
import com.buccodev.bookstore.entity.dto.OrderDTO;
import com.buccodev.bookstore.services.OrderService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){


        List<OrderDTO> list = service.findAllOrders();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO){

        service.saveOrder(orderDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addOrdersItens(@PathVariable UUID id, @RequestBody List<OrdemItemDTO> ordemItemDTOS){

        service.addOrdenItens(id, ordemItemDTOS);

        return ResponseEntity.ok().build();
    }



}


