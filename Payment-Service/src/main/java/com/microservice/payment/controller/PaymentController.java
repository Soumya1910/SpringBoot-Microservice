package com.microservice.payment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.payment.common.PaymentResponse;
import com.microservice.payment.entity.Payment;
import com.microservice.payment.service.PaymentService;


@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
	
	final static Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/")
	public PaymentResponse doPay(@RequestBody Payment payment) throws Exception {
		logger.info("Payment request received...");
		PaymentResponse pay = this.paymentService.createPayment(payment);
		logger.info("payment request is processed successfully");
		return pay;
	}
	
	@GetMapping("/")
	public List<Payment> getAllPaymentDetails(){
		logger.info("Get All Payment details");
		List<Payment> pay_list = this.paymentService.getAllPayments();
		logger.info("All Payment response is completed.. "+pay_list);
		return pay_list;
	}
	
	@GetMapping("/payment_id/{payment_id}")
	public Payment getPaymentDetailsById(@PathVariable("payment_id") long payment_id){
		logger.info("Get Payment details for payment id : "+payment_id);
		Payment pay = this.paymentService.getPaymentDetailsByPaymentId(payment_id);
		logger.info("Get payment details.. "+pay);
		return pay;
	}
	
	@GetMapping("/order_id/{order_id}")
	public List<Payment> getPaymentDetailsByOrderId(@PathVariable("order_id") long order_id){
		logger.info("Get Payment details for Order Id: "+order_id);
		List<Payment> pay_list = this.paymentService.getPaymentDetailsByOrderId(order_id);
		logger.info("Payment details for order id "+order_id+" is completed.. "+pay_list);
		return pay_list;
	}

}
