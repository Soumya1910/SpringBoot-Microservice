package com.microservice.payment.common;

import com.microservice.payment.entity.Payment;

public class PaymentResponse {
	
	private Payment payment;
	private Order order;
	public PaymentResponse() {
		super();
	}
	public PaymentResponse(Payment payment, Order order) {
		super();
		this.payment = payment;
		this.order = order;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "PaymentResponse [payment=" + payment + ", order=" + order + "]";
	}
	
	

}