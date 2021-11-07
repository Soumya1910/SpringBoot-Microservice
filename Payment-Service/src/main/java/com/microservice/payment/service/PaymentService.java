package com.microservice.payment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.payment.entity.Payment;
import com.microservice.payment.repository.PaymentRepository;

@Service
public class PaymentService {

	final static Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private PaymentRepository paymentRepo;

	public Payment createPayment(Payment payment) {
		payment.setOrderAmount(payment.getPaymentAmount());
		Payment response_pay = this.paymentRepo.save(payment);
		return response_pay;
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
	
	
}
