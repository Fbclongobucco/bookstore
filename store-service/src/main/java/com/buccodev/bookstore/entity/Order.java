package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.buccodev.bookstore.entity.enuns.PaymentMethod;
import com.buccodev.bookstore.entity.enuns.StatusPayment;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	private LocalDate deliveryDate;
	
	@Column(nullable = false)
	private StatusPayment orderStatus;
	
	@OneToOne
	private Address addressDelivery;
	
	@Column(nullable = false)
	private PaymentMethod methodPayment;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
	private Set<OrderItem> itens = new HashSet<>();
	
	
	public Order() {
		super();
	}

	public Order(UUID id, Instant instant, LocalDate deliveryDate,  Address addressDelivery,
			PaymentMethod methodPayment, Client client) {
		this.id = id;
		this.instant = instant;
		this.deliveryDate = deliveryDate;
		this.orderStatus = StatusPayment.WAINTING_PAYMENT;
		this.addressDelivery = addressDelivery;
		this.methodPayment = methodPayment;
		this.client = client;
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

	public StatusPayment getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(StatusPayment orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Address getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(Address addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public PaymentMethod getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(PaymentMethod methodPayment) {
		this.methodPayment = methodPayment;
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
