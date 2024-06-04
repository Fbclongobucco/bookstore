package com.buccodev.bookstore.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_publisher")
public class Publisher extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 50, unique = true)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String country;
	
	@OneToMany(mappedBy = "publisher")
	private Set<Book> books = new HashSet<>();
	
	
	public Publisher() {
	}

	public Publisher(UUID id, String name, String country) {
		super(id);
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public Set<Book> getBooks() {
		return books;
	}

}
