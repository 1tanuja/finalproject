package com.fooddelivery.serviceimpl;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fooddelivery.entity.Cart;
import com.fooddelivery.entity.Food;
import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.User;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.CartRepository;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.services.CartService;
import com.fooddelivery.services.FoodService;
import com.fooddelivery.services.OrderService;
import com.fooddelivery.services.UserService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CartRepository cartRepository;
	
	



	@Override
	public Order updateOrder(Order order, long orderId) {
		Order newOrder = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("order", "id", orderId));
		newOrder.setQuantity(order.getQuantity());
		
		newOrder.setTotalPrice(order.getTotalPrice());
		newOrder.setPaymentStatus(order.getPaymentStatus());
		newOrder.setOrderStatus(order.getOrderStatus());
		newOrder.setOrderedDate(order.getOrderedDate());
		return orderRepository.save(newOrder);	
	}



	@Override
	public List<Order> getAllOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		Date d = new Date(month, day, year);
		List<Order> allOrder = orderRepository.findAll();
		return allOrder;
	}



	@Override
	public List<Order> getOrderByUser(long userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		Date d = new Date(month, day, year);
		List<Order> order = orderRepository.findOrderByUserId(userId);
		return order;
	}



	@Override
	public void deleteOrder(long orderId) {
		
		orderRepository.deleteById(orderId);
		
	}



	@Override
	public Order getOrderById(long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("order", "id", orderId));
		return order;
	}



	@Override
	public Order addOrderItem(Order order, long userId,long foodId) {
		User user = userService.findByUserId(userId);
		Food food = foodService.getFoodById(foodId);
		
		order.setQuantity(order.getQuantity());
		order.setTotalPrice(food.getPrice()*order.getQuantity());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderedDate(order.getOrderedDate());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setUser(user);
		Order order1 = orderRepository.save(order);
		
		return order1;
	}



	@Override
	public Order addOrder(Order order, long userId) {
		  User user = userService.getUserById(userId);
		order.setTotalPrice(order.getTotalPrice());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderedDate(order.getOrderedDate());
		order.setUser(user);
		Order o = orderRepository.save(order);
		return o;
	}
	
	
	
	
	
	

}
