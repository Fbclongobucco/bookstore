package com.buccodev.bookstore.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.buccodev.bookstore.entity.enuns.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "tb_book")
public class Book extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Integer quantityStock;
	
	@Column(nullable = false)
	private Integer category;
	
	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "tb_book_author",
	joinColumns = @JoinColumn(name="book_id"),
	inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();
	
	public Book() {
	}

	public Book(UUID id, String title, LocalDate date, BigDecimal price, Integer quantityStock, Category category, Publisher publisher) {
		super(id);
		this.title = title;
		this.date = date;
		this.price = price;
		this.quantityStock = quantityStock;
		this.publisher = publisher;
		this.setCategory(category);
		
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}

	public Category getCategory() {
		return Category.valueOf(category);
	}

	public void setCategory(Category category) {
		if(category != null) {
			this.category = category.getCode();
		}
	}
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}
	
}
