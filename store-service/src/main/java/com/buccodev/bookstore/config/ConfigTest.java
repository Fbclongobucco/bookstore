package com.buccodev.bookstore.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.buccodev.bookstore.entity.*;
import com.buccodev.bookstore.entity.dto.AddressDTO;
import com.buccodev.bookstore.entity.dto.ClientDTO;
import com.buccodev.bookstore.entity.enuns.PaymentMethod;
import com.buccodev.bookstore.repositories.*;
import com.buccodev.bookstore.services.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.buccodev.bookstore.entity.enuns.Category;

@Configuration
public class ConfigTest implements CommandLineRunner {

	@Autowired
	private PublisherRepository publisherRepository;

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
	private Faker faker;


	@Transactional
	public void run(String... args) throws Exception {


		for (int i = 0; i < 150; i ++){

			Publisher publisher = new Publisher(null, faker.book().publisher(), faker.country().name());

			publisherRepository.save(publisher);

			int valueCat = faker.number().numberBetween(0, 6);

			Book book = new Book(null, faker.book().title() + faker.number().numberBetween(1, 32), LocalDate.of(1933, 3, 8),
					new BigDecimal(faker.number().randomDouble(4,1, 4)), Category.valueOf(valueCat) , publisher
					);
			book.setQuantityStock(faker.number().numberBetween(1,20));

			bookRepository.save(book);

		}


	}
}
