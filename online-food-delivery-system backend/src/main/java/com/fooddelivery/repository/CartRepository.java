package com.fooddelivery.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Cart;
import com.fooddelivery.entity.Food;
import com.fooddelivery.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findByUser(User user);

	

	Cart findByUserAndFood(User user, Food food);

	

	

	

}
