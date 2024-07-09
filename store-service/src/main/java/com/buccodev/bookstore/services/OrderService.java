package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.repositories.OrderRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    public UUID saveOrder(Order order){

        try{

            UUID uuid = repository.save(order).getId();

            return uuid;

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Order findOrderById(UUID id){

        Optional<Order> order = repository.findById(id);

        return order.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public List<Order> findAllClient(){

        return repository.findAll();
    }

    public void deleteOrderById(UUID id){

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public Order updateOrder(UUID id, Order newOrder){

        try{
            Order order = repository.findById(id).get();

            updateData(order, newOrder);

            return repository.save(order);

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Order oldOrder, Order newOrder) {

        oldOrder.setAddressDelivery(newOrder.getAddressDelivery());
        oldOrder.setMethodPayment(newOrder.getMethodPayment());
        oldOrder.setOrderStatus(newOrder.getOrderStatus());
        oldOrder.setDeliveryDate(newOrder.getDeliveryDate());
        oldOrder.setInstant(newOrder.getInstant());
    }

}
