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

@Service
public class PaymentOrderService {
	
	final static Logger logger = LoggerFactory.getLogger(PaymentOrderService.class);
	
	final static String ORDER_BASE_URL = "http://ORDER-SERVICE/api/v1/orders";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "fallbackGetOrderDetails", threadPoolKey = "paymentOrderServicePool", threadPoolProperties = {
			
			@HystrixProperty(name = "coresize", value="20"),
			@HystrixProperty(name = "maxQueueSize", value="10"),
		}
	)
	public Order getOrderDetails(long order_id) {
		Order order = this.restTemplate.getForObject(ORDER_BASE_URL+"/order_id/"+order_id, Order.class);
		logger.info("Get Order Details in payment module: "+order);
		return order;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackPaymentStatusUpdateInOrder")
	public int paymentStatusUpdateInOrder(long order_id, String payment_status) {
		ResponseEntity<Integer> response = this.restTemplate.exchange(ORDER_BASE_URL+"/order_id/"+order_id+"/payment_status/"+payment_status, HttpMethod.PUT, null, Integer.class);
		System.out.println("payment status update done");
		return response.getBody().intValue();
	}
	
	public Order fallbackGetOrderDetails(long order_id) {
		return new Order(order_id, "item not found", 0,0,0, null);
	}
	
	public int fallbackPaymentStatusUpdateInOrder(long order_id, String payment_status) {
		return 0;
	}

}
