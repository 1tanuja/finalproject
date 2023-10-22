package com.fooddelivery.services;

import java.util.List;

import com.fooddelivery.entity.Cart;

import jakarta.validation.Valid;

public interface CartService {

	Cart addToCart(Cart cart, long userId, long foodId, int quantity);

	List<Cart> getAllCart();

	Cart getCartById(long cartId);

	Cart updateCart(Cart cart, long cartId);

	void deleteCart(long cartId);

	Cart addCart( Cart cart, long foodId, long userId);

	

	

	

}
