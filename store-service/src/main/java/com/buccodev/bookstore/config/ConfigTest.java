package com.buccodev.bookstore.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import com.buccodev.bookstore.entity.*;
import com.buccodev.bookstore.entity.enuns.FlagCard;
import com.buccodev.bookstore.entity.enuns.PayMethod;
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

		Publisher publisher = new Publisher(null,"belford roxp", "heliopolis");

		Client client = new Client(null, "Fabricio", "longobucco@gmail.com", "123399933", "929nnsin");



		Order order = new Order(null, Instant.now(), PaymentMethod.CREDIT, client);
		Book book = new Book(null, "O quinze", LocalDate.of(1923, 12, 9), BigDecimal.valueOf(20), 12, Category.AUTOBIOGRAPH, publisher);


		OrderItem item = new OrderItem(order, book, 2);
		OrderItem item1 = new OrderItem(order, book, 3);

		book.getItens().add(item);
		book.getItens().add(item1);


		order.setDeliveryDate(LocalDate.of(2024, 8, 22));


		publisherRepo.save(publisher);
		clientRepository.save(client);



		service.saveOrder(order);

		bookRepository.save(book);







	}
}
