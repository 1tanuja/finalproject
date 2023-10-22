package com.fooddelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.entity.Admin;
import com.fooddelivery.services.AdminService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin ){
	     Admin registerAdmin = adminService.registerAdmin(admin);
	     return new ResponseEntity<>(registerAdmin,HttpStatus.CREATED);
	     
	}
	
	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin){
		Admin loginAdmin = adminService.loginAdmin(admin);
		return new ResponseEntity<>(loginAdmin,HttpStatus.ACCEPTED);
		
	}
	

}
