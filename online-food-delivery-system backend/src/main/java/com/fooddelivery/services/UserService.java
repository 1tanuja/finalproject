package com.fooddelivery.services;

import com.fooddelivery.entity.User;

public interface UserService {
	User register(User user);
	User authenticate(User user);
	User findByUserId(long userId);
	User updateUser(User user,long userId);
	User getUserById(long userId);
	User getUserByEmail(User user);
	
   
}
