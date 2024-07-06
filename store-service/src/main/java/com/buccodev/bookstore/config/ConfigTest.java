package com.buccodev.bookstore.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import com.buccodev.bookstore.entity.*;
import com.buccodev.bookstore.entity.enuns.FlagCard;
import com.buccodev.bookstore.entity.enuns.PayMethod;
import com.buccodev.bookstore.entity.enuns.PaymentMethod;
import com.buccodev.bookstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.buccodev.bookstore.entity.enuns.Category;

@Configuration
public class ConfigTest implements CommandLineRunner{

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
	private AddressRepository  addressRepository;

	@Autowired
	private CardRepository cardRepository;


	@Transactional
	public void run(String... args) throws Exception {
		Publisher publisher1 = new Publisher(null, "Abril", "Brazil");
		Publisher publisher2 = new Publisher(null, "Globo", "Brazil");

		Author author1 = new Author(null, "Machado de Assis", "Brazil", LocalDate.of(1839, 6, 21));
		Author author2 = new Author(null, "Raquel de Queiroz", "Brazil", LocalDate.of(1912, 10, 2));

		Book book1 = new Book(null, "Brás Cubas", LocalDate.of(1839, 1, 1), new BigDecimal("80.21"), 28, Category.ROMANCE, publisher1);
		Book book2 = new Book(null, "O Quinze", LocalDate.of(1941, 1, 1), new BigDecimal("29.238"), 12, Category.ROMANCE, publisher2);

		Client cli1 = new Client(null, "Fabricio Longo Bucco", "longobucco@gmail.com", "13496192720", "F@b917109");

		Address address = new Address(null, "Bel", "insuin", "uwuhw", "ijwijw", "ihjwiwi", cli1);

		Card card = new Card(null, cli1, "822", LocalDate.of(2026, 7, 1), "122", PayMethod.CREDIT, FlagCard.MASTER_CARD);

		Order order1 = new Order(null, Instant.now(), PaymentMethod.CREDIT, cli1);


		OrderItem orderItem = new OrderItem(order1, book1, 12);


		// Associar orderItem com order1



		cli1.getAddress().add(address);


		cli1.getCards().add(card);


		publisherRepo.saveAll(Arrays.asList(publisher1, publisher2));
		authorRepository.saveAll(Arrays.asList(author1, author2));
		bookRepository.saveAll(Arrays.asList(book1, book2));


		book1.getAuthors().add(author1);
		book2.getAuthors().add(author2);


		publisher1.getBooks().add(book1);
		publisher2.getBooks().add(book2);


		bookRepository.saveAll(Arrays.asList(book1, book2));


		clientRepository.save(cli1);


		addressRepository.save(address);


		cardRepository.save(card);


		orderRepository.save(order1);
	}
}
