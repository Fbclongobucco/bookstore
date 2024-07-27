package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import com.buccodev.bookstore.entity.enuns.PaymentMethod;
import com.buccodev.bookstore.entity.enuns.StatusPayment;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant instant;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private LocalDate deliveryDate;

	@ManyToOne
	@JoinColumn(name = "addressDelivery_id")
	private Address addressDelivery;

	@Column(nullable = false)
	private Integer orderStatus;

	@Column(nullable = false)
	private Integer methodPayment;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderItem> itens = new HashSet<>();
	
	
	public Order() {
	}

	public Order(UUID id, Instant instant, PaymentMethod methodPayment, Client client) {
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.setOrderStatus(StatusPayment.WAINTING_PAYMENT);
		this.setMethodPayment(methodPayment);
	}

	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Address getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(Address addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<OrderItem> getItens() {
		return itens;
	}

	public StatusPayment getOrderStatus() {
		return StatusPayment.valueOf(orderStatus);
	}

	public void setOrderStatus(StatusPayment orderStatus) {
		this.orderStatus = orderStatus.getCode();
	}

	public PaymentMethod getMethodPayment() {
		return PaymentMethod.valueOf(methodPayment);
	}

	public void setMethodPayment(PaymentMethod methodPayment) {
		if(methodPayment != null){
			this.methodPayment = methodPayment.getCode();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(deliveryDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(deliveryDate, other.deliveryDate);
	}

}
