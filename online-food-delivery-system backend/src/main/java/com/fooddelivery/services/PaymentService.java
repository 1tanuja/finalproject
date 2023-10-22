package com.fooddelivery.services;

import java.util.List;

import com.fooddelivery.entity.Payment;

public interface PaymentService {

	Payment makepayment(Payment payment, long orderId, long userId);

	List<Payment> getAllPayment();

	Payment getById(long paymentId);

	List<Payment> getByUser(long userId);

	void deletePayment(long paymentId);

}
