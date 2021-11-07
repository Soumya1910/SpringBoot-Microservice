package com.microservice.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_details")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_seq")
	@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price_per_item")
	private double pricePerItem;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	

	public Order() {
		super();
	}



	public Order(Long orderId, String itemName, int quantity, double pricePerItem, double totalPrice,
			String paymentStatus) {
		super();
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
		this.totalPrice = totalPrice;
		this.paymentStatus = paymentStatus;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getPricePerItem() {
		return pricePerItem;
	}



	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getPaymentStatus() {
		return paymentStatus;
	}



	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", itemName=" + itemName + ", quantity=" + quantity + ", pricePerItem="
				+ pricePerItem + ", totalPrice=" + totalPrice + ", paymentStatus=" + paymentStatus + "]";
	}
	
	

}
