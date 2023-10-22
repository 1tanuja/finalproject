package com.fooddelivery.services;



import java.util.List;

import com.fooddelivery.entity.Order;

public interface OrderService {

	

	Order updateOrder(Order order, long orderId);

	List<Order> getAllOrder();

	List<Order> getOrderByUser(long userId);

	void deleteOrder(long orderId);

	Order getOrderById(long orderId);

	Order addOrderItem(Order order, long userId, long foodId);

	Order addOrder(Order order, long userId);

}
