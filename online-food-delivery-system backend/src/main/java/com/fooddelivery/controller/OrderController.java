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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.entity.Order;
import com.fooddelivery.services.OrderService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	
	@PostMapping("/addOrder/{userId}")
	public ResponseEntity<Order> addOrder(@PathVariable("userId")long userId,@RequestBody Order order) {
		Order addOrder = orderService.addOrder(order,userId);
		return new ResponseEntity<Order>(addOrder,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable("orderId")long orderId){
		Order updateOrder = orderService.updateOrder(order,orderId);
		return new ResponseEntity<>(updateOrder,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllOrder")
	public List<Order> getAllOrders(){
		return orderService.getAllOrder();
	}
	
	@GetMapping("/get/{userId}")
	public List<Order> getOrderByUser(@PathVariable("userId")long userId){
		return orderService.getOrderByUser(userId);
	}
	
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId")long orderId) {
		orderService.deleteOrder(orderId);
		return new ResponseEntity<>("Order deleted Successfully",HttpStatus.OK); 
		
	}
	
	@GetMapping("/getById/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable("orderId")long orderId){
		Order order = orderService.getOrderById(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PostMapping("/addOrder/{userId}/{foodId}")
	public ResponseEntity<Order> addOrderItem(@RequestBody Order order,@PathVariable("userId")long userId,@PathVariable("foodId") long foodId){
		Order newOrder = orderService.addOrderItem(order,userId,foodId);
		return new ResponseEntity<>(newOrder,HttpStatus.OK);
	}
	
	

	
}
