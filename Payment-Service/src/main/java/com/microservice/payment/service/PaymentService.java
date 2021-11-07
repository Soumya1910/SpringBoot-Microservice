package com.microservice.payment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.payment.common.Order;
import com.microservice.payment.common.PaymentResponse;
import com.microservice.payment.entity.Payment;
import com.microservice.payment.repository.PaymentRepository;

@Service
public class PaymentService {

	final static Logger logger = LoggerFactory.getLogger(PaymentService.class);
	final static String ORDER_BASE_URL = "http://localhost:8001/api/v1/orders";
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private RestTemplate restTemplate;

	public PaymentResponse createPayment(Payment payment) throws Exception {
		logger.info("create payment service called...");
		PaymentResponse payResponse = new PaymentResponse();
		Order order = this.getOrderDetails(payment.getOrderId());
		logger.info("Order details : "+order);
		if(order == null) {
			throw new Exception("Order details not found.");
		}
		payment.setOrderAmount(order.getTotal_price());
		Payment response_pay = this.paymentRepo.save(payment);
		String payment_status = (response_pay.getId()!= null)? "Success": "Failure";
		int status = this.paymentStatusUpdateInOrder(payment.getOrderId(), payment_status);
		order.setPayment_status(payment_status);
		payResponse.setPayment(response_pay);
		payResponse.setOrder(order);
		return payResponse;
	}
	
	public List<Payment> getAllPayments() {
		logger.info("Get all payment details");
		return this.paymentRepo.findAll();
	}
	
	public Payment getPaymentDetailsByPaymentId(long pay_id) {
		logger.info("Get payment details for payment_id: "+pay_id);
		Payment payment = this.paymentRepo.findById(pay_id).orElse(null);
		logger.info("Response payment details for payment_id: "+pay_id+"\n"+payment);
		return payment;
	}
	
	public List<Payment> getPaymentDetailsByOrderId(double order_id) {
		logger.info("Get payment details for order_id: "+order_id);
		List<Payment> payment_list = this.paymentRepo.findByOrderId((long)order_id);
		logger.info("Response payment details for payment_id: "+order_id+"\n"+payment_list);
		return payment_list;
	}
	
	private Order getOrderDetails(long order_id) {
		Order order = this.restTemplate.getForObject(ORDER_BASE_URL+"/order_id/"+order_id, Order.class);
		logger.info("Get Order Details in payment module: "+order);
		return order;
	}
	
	private int paymentStatusUpdateInOrder(long order_id, String payment_status) {
		ResponseEntity<Integer> response = this.restTemplate.exchange(ORDER_BASE_URL+"/order_id/"+order_id+"/payment_status/"+payment_status, HttpMethod.PUT, null, Integer.class);
		System.out.println("payment status update done");
		return response.getBody().intValue();
	}
	
	
}
