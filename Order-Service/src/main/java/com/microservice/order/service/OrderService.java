package com.microservice.order.service;

import java.util.List;

import javax.transaction.Transactional;

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
		double d = Double.parseDouble(String.format("%.2f",order.getPrice_per_item() * order.getQuantity()));
		order.setTotal_price(d);
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
	
	@Transactional
	public int updatePaymentStatus(long order_id, String payment_status) {
		logger.info("order update for "+order_id);
		int updateStatus = this.orderRepo.updatePaymentStatus(payment_status, order_id);
		return updateStatus;
	}

}
