package com.hcl.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Laxman
 *
 */
@Entity
@Table
@NoArgsConstructor
@Setter
@Getter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long mobileNumber;
}
