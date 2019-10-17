package com.hcl.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
}
