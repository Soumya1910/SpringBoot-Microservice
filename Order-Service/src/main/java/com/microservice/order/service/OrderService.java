package com.microservice.order.service;

import java.util.Formatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.order.entity.Order;
import com.microservice.order.repository.OrderRepository;

@Service
public class OrderService {
	
	final static Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepo;

	public Order createOrder(Order order) {
		logger.info(order.toString());
		double d = Double.parseDouble(String.format("%.2f",order.getPricePerItem() * order.getQuantity()));
		order.setTotalPrice(d);
		return orderRepo.save(order);
	}

	public List<Order> getAllOrders() {
		List<Order> orderList = this.orderRepo.findAll();
		logger.info("Order list : "+orderList);
		return orderList;
	}

	public Order getOrderById(long order_id) {
		Order order = this.orderRepo.findById(order_id).orElse(null);
		logger.info("Single order : "+order);
		return order;
	}

}
