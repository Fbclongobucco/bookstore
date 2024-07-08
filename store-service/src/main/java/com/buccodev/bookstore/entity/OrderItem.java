package com.buccodev.bookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.buccodev.bookstore.entity.pk.OrderItemPK;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	@ManyToOne
	@MapsId("order")
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@MapsId("book")
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal totalPrice;

	public OrderItem(){
	}

	public OrderItem(Order order, Book book, Integer quantity) {
		this.id.setOrder(order);
		this.id.setBook(book);
		this.quantity = quantity;
		this.totalPrice= sumTotal();

	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal sumTotal() {
		return id.getBook().getPrice().multiply(BigDecimal.valueOf(quantity));
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderItem orderItem = (OrderItem) o;
		return Objects.equals(id, orderItem.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
