package com.buccodev.bookstore.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.buccodev.bookstore.entity.Author;
import com.buccodev.bookstore.entity.Book;
import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.entity.enuns.Category;
import com.buccodev.bookstore.repositories.AuthorRepository;
import com.buccodev.bookstore.repositories.BookRepository;
import com.buccodev.bookstore.repositories.PublisherRepository;

@Configuration
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private PublisherRepository publisherRepo;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Publisher publisher1 = new Publisher(null, "Abril", "Brazil");
		Publisher publisher2 = new Publisher(null, "Globo", "Brazil");
		
		
		
		Author author1 = new Author(null, "Machado de Assis", "Brazil", LocalDate.of(1839, 6, 21));
		Author author2 = new Author(null, "Raquel de queiroz", "Brazil", LocalDate.of(1912, 10, 2));
		
		
		
		Book book1 = new Book(null, "Brás Cubas", LocalDate.of(1839, 1, 1), new BigDecimal("80.21") , 28, Category.ROMANCE, publisher1);
		Book book2 = new Book(null, "O Quinze", LocalDate.of(1941, 1, 1), new BigDecimal("29.238") , 12, Category.ROMANCE, publisher2);
		
		
		publisherRepo.saveAll(Arrays.asList(publisher1, publisher2));
		authorRepository.saveAll(Arrays.asList(author1,author2));
		bookRepository.saveAll(Arrays.asList(book1, book2));
		

		book1.getAuthors().add(author1);
		book2.getAuthors().add(author2);
		
		
	
		bookRepository.saveAll(Arrays.asList(book1, book2));
		
	}
	
	

}
