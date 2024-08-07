package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.buccodev.bookstore.entity.enuns.Category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_book")
public class Book implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
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

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "tb_book_authors",
	joinColumns = @JoinColumn(name = "book_id"),
	inverseJoinColumns = @JoinColumn(name = "Author_id"))
	private Set<Author> authors = new HashSet<>();

	public Book(){
	}


	public Book(UUID id, String title, LocalDate date, BigDecimal price, Category category, Publisher publisher) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.price = price;
		this.quantityStock = 0;
		this.publisher = publisher;
		this.setCategory(category);

	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}

}
