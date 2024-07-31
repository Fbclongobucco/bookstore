package com.buccodev.bookstore.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.buccodev.bookstore.entity.*;
import com.buccodev.bookstore.entity.enuns.PaymentMethod;
import com.buccodev.bookstore.repositories.*;
import com.buccodev.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.buccodev.bookstore.entity.enuns.Category;

@Configuration
public class ConfigTest implements CommandLineRunner {

	@Autowired
	private PublisherRepository publisherRepo;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private OrderService service;

	@Transactional
	public void run(String... args) throws Exception {





	}
}
