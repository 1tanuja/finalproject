package com.fooddelivery.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.Payment;
import com.fooddelivery.entity.User;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.PaymentRepository;
import com.fooddelivery.services.OrderService;
import com.fooddelivery.services.PaymentService;
import com.fooddelivery.services.UserService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Payment makepayment(Payment payment, long orderId, long userId) {
		Order order = orderService.getOrderById(orderId);
		User user = userService.findByUserId(userId);
		payment.setOrderId(order.getOrderId());
		payment.setPaidDate(LocalDate.now());
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidAmount(order.getTotalPrice());
		if(payment.getTotalPrice()==payment.getPaidAmount()) {
			order.setPaymentStatus("Paid");
		}else {
			order.setPaymentStatus("Payment pending");
		}
		payment.setUser(user);
		Payment save = paymentRepository.save(payment);
		return save;
	}

	@Override
	public List<Payment> getAllPayment() {
		List<Payment> payment = paymentRepository.findAll();
		return payment;
	}

	@Override
	public Payment getById(long paymentId) {
		Payment payment = paymentRepository.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException("payment", "id", paymentId));
		return payment;
	}

	@Override
	public List<Payment> getByUser(long userId) {
		User user = userService.findByUserId(userId);
		List<Payment> payment = paymentRepository.findPaymentByUser(user);
		return payment;
	}

	@Override
	public void deletePayment(long paymentId) {
		paymentRepository.deleteById(paymentId);
		
	}

}
