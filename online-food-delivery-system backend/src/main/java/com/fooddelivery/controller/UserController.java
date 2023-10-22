package com.fooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.fooddelivery.entity.User;

import com.fooddelivery.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.register(user);
        System.out.println("user registred ! "+ user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
    	System.out.println("Login successfull with username "+user.getEmail());
    	return new ResponseEntity<User>(userService.authenticate(user), HttpStatus.OK);
    }
    
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("userId")long userId){
    	User updateUser = userService.updateUser(user,userId);
    	return new ResponseEntity<>(updateUser,HttpStatus.ACCEPTED);
    			}
    
    
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId")long userId) {
    	User userById = userService.getUserById(userId);
    	return new ResponseEntity<>(userById,HttpStatus.OK);
    }
    
    @PostMapping("/forgotpassword")
	public User getUserByEmail(@RequestBody User user) {
		return userService.getUserByEmail(user);
	}
    
    @PostMapping("/{userId}/{newpassword}")
	public User changeCustomerPassword(@PathVariable("userId") long userId,@PathVariable("newpassword") String newpassword) {
		//return customerService.getCustomerByEmail(customer);
		User u=userService.getUserById(userId);
		u.setPassword(newpassword);
		userService.updateUser(u, userId);
		return u;
	}

}






