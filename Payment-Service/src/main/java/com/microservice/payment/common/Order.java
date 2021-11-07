package com.microservice.payment.common;


public class Order {

	private Long order_id;
	private String item_name;
	private int quantity;
	private double price_per_item;
	private double total_price;
	private String payment_status;
	
	public Order() {
		super();
	}
	
	public Order(Long order_id, String item_name, int quantity, double price_per_item, double total_price,
			String payment_status) {
		super();
		this.order_id = order_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.price_per_item = price_per_item;
		this.total_price = total_price;
		this.payment_status = payment_status;
	}
	
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice_per_item() {
		return price_per_item;
	}

	public void setPrice_per_item(double price_per_item) {
		this.price_per_item = price_per_item;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", item_name=" + item_name + ", quantity=" + quantity
				+ ", price_per_item=" + price_per_item + ", total_price=" + total_price + ", payment_status="
				+ payment_status + "]";
	}	
}
