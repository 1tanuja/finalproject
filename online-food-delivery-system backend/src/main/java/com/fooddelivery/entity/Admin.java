package com.fooddelivery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_table",	
uniqueConstraints = @UniqueConstraint(columnNames = "adminEmailId"))
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(min=3 , message="firstName must contain atleast 3 characters")
	private String firstName;
	
	@NotEmpty
	@Size(min=3 , message="lastName must contain atleast 3 characters")
	private String lastName;
	
	@NotEmpty
	@Email(message = "Email id is not valid!")
	@Column(unique = true)
	private String adminEmailId;
	
	@NotEmpty
	@Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	private String adminPassword;
	
	

}
