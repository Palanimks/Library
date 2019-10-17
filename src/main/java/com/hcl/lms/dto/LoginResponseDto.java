package com.hcl.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginResponseDto {

	private String status;
	private String message;
	private String firstName;
	private Integer userId;
}
