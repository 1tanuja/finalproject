package com.fooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.entity.Payment;
import com.fooddelivery.services.PaymentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("{orderId}/{userId}")
	public ResponseEntity<Payment> makePayment(@RequestBody Payment payment,@PathVariable("userId")long userId,@PathVariable("orderId")long orderId){
		Payment makepayment = paymentService.makepayment(payment,orderId,userId);
		return new ResponseEntity<>(makepayment,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("getAll")
	public List<Payment> getAllPayment(){
		return paymentService.getAllPayment();
	}
	
	@GetMapping("get/{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable("paymentId")long  paymentId){
		return new ResponseEntity<>(paymentService.getById(paymentId),HttpStatus.OK);
	}
	
	@GetMapping("getByUser/{userId}")
	public List<Payment> getPamentByUser(@PathVariable("userId")long userId){
		 List<Payment> payment = paymentService.getByUser(userId);
		 return payment;
	}
	
	@DeleteMapping("delete/{paymentId}")
	public String deletePayment(@PathVariable("paymentId")long  paymentId) {
		paymentService.deletePayment(paymentId);
		return "payement deleted successfully";
	}

}
