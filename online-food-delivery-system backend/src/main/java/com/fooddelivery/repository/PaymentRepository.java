package com.fooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Payment;
import com.fooddelivery.entity.User;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	

	

	List<Payment> findPaymentByUser(User user);

}
