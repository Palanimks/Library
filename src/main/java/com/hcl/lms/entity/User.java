package com.hcl.lms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Setter
@Getter
public class User {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long mobileNumber;

}
