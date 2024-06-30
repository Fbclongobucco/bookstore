package com.buccodev.bookstore.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Client {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 100)
	private String name;
	 
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	@Column(nullable = false, length = 100)
	private String cpf;
	
	@Column(nullable = false, length = 50)
	private String password;
	
	@OneToMany
	Set<Card> cards = new HashSet<>();
	
	@OneToMany
	Set<Address> address = new HashSet<>();


	public Client() {
		
	}
	
	
	public Client(UUID id, String name, String email, String cpf, String password, Set<Card> cards) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.password = password;
		this.cards = cards;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Card> getCards() {
		return cards;
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
