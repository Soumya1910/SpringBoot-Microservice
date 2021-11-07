package com.microservice.order.controller;

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

import com.microservice.order.entity.Order;
import com.microservice.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	final static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/")
	public Order placeOrder(@RequestBody Order order) {
		logger.info("Place order request received --- controller"+order);
		Order response = this.orderService.createOrder(order);
		logger.info("Order response: "+response);
		return response;
	}
	
	@GetMapping("/")
	public List<Order> findAllOrders(){
		logger.info("Find all orders request received --- controller");
		List<Order> orderList = this.orderService.getAllOrders();
		logger.debug("Final response for all orders");
		return orderList;
	}
	
	@GetMapping("/order_id/{order_id}")
	public Order getOrderById(@PathVariable("order_id") long order_id) {
		logger.info("Get a single order request received for order id : "+order_id+"--- controller");
		Order order = this.orderService.getOrderById(order_id);
		logger.debug("Final response for a single order");
		return order;
	}

}
