package com.microservice.payment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="payment_details")
public class Payment {
	
	private static final long serialVersionUID = 4865903039190150223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_seq")
	@SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
	@Column(name = "payment_id")
	private Long id;
	
	@Column(name = "payment_amount", nullable = false)
	private Double paymentAmount;
	
	@Column(name = "order_id", nullable = false)
	private Long orderId;
	
	@Column(name = "order_amount", nullable = false)
	private Double orderAmount;
	
	@Column(name = "payment_mode", nullable = false)
	private String paymentMode;

	public Payment() {
		super();
	}

	public Payment(Long id, Double paymentAmount, Long orderId, Double orderAmount, String paymentMode) {
		super();
		this.id = id;
		this.paymentAmount = paymentAmount;
		this.orderId = orderId;
		this.orderAmount = orderAmount;
		this.paymentMode = paymentMode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", paymentAmount=" + paymentAmount + ", orderId=" + orderId + ", orderAmount="
				+ orderAmount + ", paymentMode=" + paymentMode + "]";
	}
	
	


}
