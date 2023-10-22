package com.fooddelivery.entity;



import jakarta.validation.constraints.Pattern;


import jakarta.validation.constraints.Email;

import java.sql.Date;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_table",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"phoneNo"}),
        @UniqueConstraint(columnNames = {"email"})
}
)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(min=3 , message="First Name should contain atleast 3 characters")
	private String firstname;
	
	@NotEmpty
	@Size(min=3, message = "Last Name should conatin atleast 3 character")
	private String lastname;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@NotEmpty
	@Email(message = "email is not valid!")
	private String email;
	
	@NotEmpty
	@Size(min=10,message="mobile Number is not valid !")
	@Pattern(regexp ="^\\d{10}$")
	private String phoneNo;
	
	@NotEmpty
	@Size(min=4,message="gender must contain atleast 4 characters")
	private String gender;
	@Lob
	@NotEmpty
	private String address;
	@NotEmpty
	private String district;
	@NotEmpty
	@Size(min=6,message = "please enter correct zipcode")
	private String zipcode;
	@NotEmpty
	private String state;
	
	@NotEmpty
	@Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	private String password;
	
	
	

}
