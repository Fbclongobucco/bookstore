package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Client;
import com.buccodev.bookstore.entity.Order;
import com.buccodev.bookstore.entity.OrderItem;
import com.buccodev.bookstore.entity.dto.OrdemItemDTO;
import com.buccodev.bookstore.entity.dto.OrderDTO;
import com.buccodev.bookstore.repositories.BookRepository;
import com.buccodev.bookstore.repositories.ClientRepository;
import com.buccodev.bookstore.repositories.OrderItemRepository;
import com.buccodev.bookstore.repositories.OrderRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public void saveOrder(OrderDTO orderDTO) {

        Order order = OrderDTO.toOrderFromDTO(orderDTO);

        Client client = clientRepository.findById(orderDTO.idClient()).orElseThrow(()-> new ResourceNotFoundException(orderDTO.idClient()));

        order.setClient(client);

        client.getOrders().add(order);

        clientRepository.save(client);

        repository.save(order);

    }


    public OrderDTO findOrderById(UUID id){

        Order order = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

        return OrderDTO.fromOrder(order);
    }

    public List<OrderDTO> findAllOrders(){

        List<Order> list = repository.findAll();

        return list.stream().map(OrderDTO::fromOrder).toList();
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

    public void addOrdenItens(UUID id, List<OrdemItemDTO> ordemItemDTOS){

        Order order = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

        List<OrderItem> orders = new ArrayList<>();

        for (OrdemItemDTO ordemItemDTO : ordemItemDTOS){

            var book = bookRepository.findById(ordemItemDTO.idBook()).orElseThrow(()-> new ResourceNotFoundException(ordemItemDTO.idBook()));

            OrderItem orderItem = new OrderItem(order, book, ordemItemDTO.quantity());

            orders.add(orderItem);

            orderItemRepository.save(orderItem);
        }

        order.getItens().addAll(orders);

        repository.save(order);

    }

    public void updateOrder(UUID id, OrderDTO newOrderDTO){

        Order newOrder = OrderDTO.toOrderFromDTO(newOrderDTO);

        try{
            Order order = repository.findById(id).get();

            updateData(order, newOrder);

             repository.save(order);

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Order oldOrder, Order newOrder) {

        oldOrder.setMethodPayment(newOrder.getMethodPayment());
        oldOrder.setOrderStatus(newOrder.getOrderStatus());
        oldOrder.setDeliveryDate(newOrder.getDeliveryDate());
        oldOrder.setInstant(newOrder.getInstant());
    }

}
