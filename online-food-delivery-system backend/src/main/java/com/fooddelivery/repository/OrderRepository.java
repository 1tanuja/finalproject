package com.fooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	

	List<Order> findOrderByUserId(long userId);

}
