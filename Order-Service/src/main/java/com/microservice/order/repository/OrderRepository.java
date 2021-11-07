package com.microservice.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Modifying
	@Query("update Order ord set ord.payment_status =:payment_status where ord.order_id =:order_id")
	int updatePaymentStatus(@Param("payment_status") String payment_status, @Param("order_id") long order_id);
}
