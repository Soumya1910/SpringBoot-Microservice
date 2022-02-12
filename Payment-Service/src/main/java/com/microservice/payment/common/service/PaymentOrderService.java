package com.microservice.payment.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.payment.common.Order;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentOrderService {
	
	final static Logger logger = LoggerFactory.getLogger(PaymentOrderService.class);
	
	final static String ORDER_BASE_URL = "http://ORDER-SERVICE/api/v1/orders";

	private static final String ORDER_SERVICE = "orderService";
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@HystrixCommand(fallbackMethod = "fallbackGetOrderDetails", threadPoolKey = "paymentOrderServicePool", threadPoolProperties = {
//			
//			@HystrixProperty(name = "coresize", value="20"),
//			@HystrixProperty(name = "maxQueueSize", value="10"),
//		}
//	)
	@CircuitBreaker(name=ORDER_SERVICE, fallbackMethod = "fallbackGetOrderDetails")
	public Order getOrderDetails(long order_id) {
		Order order = this.restTemplate.getForObject(ORDER_BASE_URL+"/order_id/"+order_id, Order.class);
		logger.info("Get Order Details in payment module: "+order);
		return order;
	}
	
	@CircuitBreaker(name=ORDER_SERVICE, fallbackMethod = "fallbackPaymentStatusUpdateInOrder")
	public int paymentStatusUpdateInOrder(long order_id, String payment_status) {
		ResponseEntity<Integer> response = this.restTemplate.exchange(ORDER_BASE_URL+"/order_id/"+order_id+"/payment_status/"+payment_status, HttpMethod.PUT, null, Integer.class);
		System.out.println("payment status update done");
		return response.getBody().intValue();
	}
	
	public Order fallbackGetOrderDetails(long order_id, Exception e) {
		return new Order(order_id, "item not found", 0,0,0, null);
	}
	
	public int fallbackPaymentStatusUpdateInOrder(long order_id, String payment_status, Exception e) {
		return 0;
	}

}
