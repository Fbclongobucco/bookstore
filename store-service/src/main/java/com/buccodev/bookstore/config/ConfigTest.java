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

		Publisher publisher = new Publisher(null,"Abril", "Brazil");

		Client client = new Client(null, "Fabricio", "longobucco@gmail.com", "123399933", "929nnsin");

		Address address = new Address(null, "Belford Roxo", "heliópolis", "Rua Tietê", "10", "Moises Pintor", client);

		client.getAddress().add(address);

		Order order = new Order(null, Instant.now(), PaymentMethod.CREDIT, client);


		Book book = new Book(null, "O quinze", LocalDate.of(1923, 12, 9), BigDecimal.valueOf(56), Category.AUTOBIOGRAPH, publisher);
		Book book2 = new Book(null, "Dom Casmurro", LocalDate.of(1923, 12, 9), BigDecimal.valueOf(20),  Category.ROMANCE, publisher);
		Author author2 = new Author(null, "Raquel de Queiroz", "Brazil", LocalDate.of(1912, 2, 20));

		Author author = new Author(null, "Machado de Assis", "Brazil", LocalDate.of(1889, 10, 10));
		OrderItem item = new OrderItem(order, book, 2);
		OrderItem item1 = new OrderItem(order, book, 3);



		authorRepository.save(author);
		authorRepository.save(author2);
		book2.getAuthors().add(author);
		book.getAuthors().add(author2);
		author.getBooks().add(book2);
		order.getItens().add(item);
		order.getItens().add(item1);

		order.setDeliveryDate(LocalDate.of(2024, 8, 22));


		publisherRepo.save(publisher);
		clientRepository.save(client);

		order.setAddressDelivery(address);


		bookRepository.save(book);
		bookRepository.save(book2);

		service.saveOrder(order);
		clientRepository.save(client);




	}
}
