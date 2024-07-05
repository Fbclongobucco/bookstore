package com.buccodev.bookstore.config;

import com.buccodev.bookstore.entity.*;
import com.buccodev.bookstore.entity.enuns.Category;
import com.buccodev.bookstore.entity.enuns.FlagCard;
import com.buccodev.bookstore.entity.enuns.PayMethod;
import com.buccodev.bookstore.entity.enuns.PaymentMethod;
import com.buccodev.bookstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class ConfigTestFix implements CommandLineRunner {

	@Autowired
	private PublisherRepository publisherRepo;

	@Autowired
	private BookRepository bookRepository;



	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {


		Publisher publisher = new Publisher(null, "ijniw", "iwhwu");

		Book book1 = new Book(UUID.randomUUID(), "Brás Cubas", LocalDate.of(1839, 1, 1), new BigDecimal("80.21"), 28, Category.ROMANCE, publisher);
		Book book2 = new Book(null, "O Quinze", LocalDate.of(1941, 1, 1), new BigDecimal("29.238"), 12, Category.ROMANCE, publisher);

		Client cli = new Client(null, "fabrrr", "ijwinw", "ikmsis", "niusnis");

		Order order = new Order(UUID.randomUUID(), Instant.now(), PaymentMethod.CREDIT, cli);

		bookRepository.save(book1);
		orderRepository.save(order);



		OrderItem oi = new OrderItem(order, book1, 2);




		orderItemRepository.save(oi);

	}
}
