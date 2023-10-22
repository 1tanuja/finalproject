package com.fooddelivery.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment_table",
uniqueConstraints = @UniqueConstraint(columnNames = "orderId"))
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	
	private  double totalPrice;

	@Column(unique = true)
	private  long orderId;

	@NotEmpty
	@Size(min=3 , message="name must contain atleast 3 characters")
	private String nameOnCard;

	@NotEmpty
	@Size(min=16 , max=16,message="cardNumber must contain 16 digits")
	@Column(unique = true)
	private String cardNumber;

	@Column(name="exp_year")
	private String expYear;

	@NotNull
	@Column(unique = true)
	private int cvv;

	@CreationTimestamp
	private LocalDate paidDate;

	@Column(nullable = false)
	private double paidAmount;

	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	
	private User user;

}
