package com.hcl.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.dto.UserDto;
import com.hcl.lms.service.UserService;

/*
 * 
 * 
 * Vijay
 */
@RequestMapping("/lms")
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })

public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto usertDto) {
		ResponseDto response = userService.addUser(usertDto);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
