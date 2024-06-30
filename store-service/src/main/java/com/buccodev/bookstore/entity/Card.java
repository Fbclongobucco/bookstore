package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.buccodev.bookstore.entity.enuns.PayMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_card")
public class Card implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 16)
	private String numCard;
	
	@Temporal(TemporalType.DATE)
	private LocalDate validity;
	
	@Column(nullable = false, length = 3)
	private String cvc;
	
	@Column(nullable = false)
	private PayMethod payMethod;
	
	@Column(nullable = false)
	private FlagCard flag;
	
	
	@ManyToOne
	@JoinColumn(name = "client_id") 
    private Client client;
	
	 
	public Card() {
		
	}
	
	
	public Card(UUID id, Client client, String numCard, LocalDate validity, String cvc, PayMethod payMethod,
			FlagCard flag) {
		this.id = id;
		this.client = client;
		this.numCard = numCard;
		this.validity = validity;
		this.cvc = cvc;
		this.payMethod = payMethod;
		this.flag = flag;
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	
    public Client getClient() { 
    	return client; 
    }
	  
	public void setClient(Client client) {
	    this.client = client; 
	}


	public String getNumCard() {
		return numCard;
	}


	public void setNumCard(String numCard) {
		this.numCard = numCard;
	}


	public LocalDate getValidity() {
		return validity;
	}


	public void setValidity(LocalDate validity) {
		this.validity = validity;
	}


	public String getCvc() {
		return cvc;
	}


	public void setCvc(String cvc) {
		this.cvc = cvc;
	}


	public PayMethod getPayMethod() {
		return payMethod;
	}


	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}


	public FlagCard getFlag() {
		return flag;
	}


	public void setFlag(FlagCard flag) {
		this.flag = flag;
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
		Card other = (Card) obj;
		return Objects.equals(id, other.id);
	}


}
