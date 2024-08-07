package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 100)
	private String city;
	
	@Column(nullable = false, length = 100)
	private String neighborhood;
	
	@Column(nullable = false, length = 100)
	private String street;
	
	@Column(nullable = false, length = 10)
	private String number;
	
	@Column(nullable = false, length = 100)
	private String complement;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;
	  
	 
	public Address() {
	}


	public Address(UUID id, String city, String neighborhood, String street, String number, String complement, Client client) {
		this.id = id;
		this.city = city;
		this.neighborhood = neighborhood;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.client = client;
	}

	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getComplement() {
		return complement;
	}


	public void setComplement(String complement) {
		this.complement = complement;
	}


	
	  public Client getClient() { return client; }
	  
	  
	  public void setClient(Client client) { this.client = client; }
	  
	 
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

}
