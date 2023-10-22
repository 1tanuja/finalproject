package com.fooddelivery.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	private long quantity;
	private double totalPrice;
	private String OrderStatus;
	private String paymentStatus;
	private Date orderedDate;
	private String foodName;
	private String image;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_Id")
	@JsonIgnore
	private User user;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name="food_id")
	private List<Food> food;


}
