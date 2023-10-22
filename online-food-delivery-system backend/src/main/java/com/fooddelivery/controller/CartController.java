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

import com.fooddelivery.entity.Cart;

import com.fooddelivery.services.CartService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("{userId}/{foodId}")
	public ResponseEntity<Cart> addCart(@Valid @RequestBody Cart cart, @PathVariable long foodId,@PathVariable long userId) {
		
		return new ResponseEntity<Cart>(cartService.addCart(cart, foodId,userId), HttpStatus.CREATED);
	}

	
	
    @PostMapping("/{userId}/{foodId}/{quantity}")
	public ResponseEntity<Cart> addTocart(@Valid Cart cart,@PathVariable("userId")long userId, @PathVariable("foodId")long foodId,@PathVariable int quantity) {
		Cart addToCart = cartService.addToCart(cart,userId,foodId,quantity);
		return new ResponseEntity<>(addToCart,HttpStatus.ACCEPTED);	
	}
    
    @GetMapping("/getAll")
    public List<Cart> getAll(){
    	List<Cart> allCart = cartService.getAllCart();
		return allCart;
    }
    
    @GetMapping("/getById/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable("cartId") long cartId){
    	Cart cart = cartService.getCartById(cartId);
    	return new ResponseEntity<>(cart,HttpStatus.OK);
    }
    
    
   
    @PutMapping("/update/{cartId}")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart,@PathVariable("cartId") long cartId){
    	Cart updateCart = cartService.updateCart(cart,cartId);
    	return new ResponseEntity<>(updateCart,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{cartId}")
	public ResponseEntity<String> deleteCart(@PathVariable long cartId) {
		cartService.deleteCart(cartId);
		return new ResponseEntity<>("Cart deleted successfully", HttpStatus.OK);
	}
    
   
   
    
    
}
