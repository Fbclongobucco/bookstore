package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.buccodev.bookstore.entity.pk.OrderItemPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false, precision = 10)
	private BigDecimal totalPrice = sumTotal();
	
	public OrderItem() {
		
	}
	
	public OrderItem(Order order, Book book, Integer quantity) {
		id.setOrder(order);
		id.setBook(book);
		this.quantity = quantity;
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void serOrder(Order order) {
		id.setOrder(order);
	}
	
	public Book getBook() {
		return id.getBook();
	}
	
	public void setBook(Book book) {
		id.setBook(book);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public BigDecimal sumTotal() {
		return id.getBook().getPrice().multiply(BigDecimal.valueOf(quantity));
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

}
