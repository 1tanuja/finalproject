package com.fooddelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    Boolean existsByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);
	User findUserByEmail(String email);
	Optional<User> findByEmail(String email);
	


}
