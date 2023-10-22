package com.fooddelivery.entity;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_table")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	
	private int quantity;
	
	@Column(nullable = false)
	private double totalPrice;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="food_Id")
	@JsonIgnore
	private Food food;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_Id")
	private User user;
	
	
	

}
