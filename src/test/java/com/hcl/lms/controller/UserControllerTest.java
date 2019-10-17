package com.hcl.lms.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.dto.UserDto;
import com.hcl.lms.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	private MockMvc mockMvc;

	@Mock
	UserServiceImpl userServiceimpl;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testAddUser() throws JsonProcessingException, Exception {

		UserDto userDto = new UserDto();
		userDto.setEmail("abc@gmail.com");
		userDto.setFirstName("vijay");
		userDto.setLastName("kumar");
		userDto.setMobileNumber(1111l);
		

		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("successfully registered");
		responseDto.setStatus("success");

		when(userServiceimpl.addUser(Mockito.any())).thenReturn(responseDto);
		mockMvc.perform(MockMvcRequestBuilders.get("registration").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(new ObjectMapper().writeValueAsString(""))).andReturn();

		ResponseEntity<ResponseDto> actual = userController.addUser(userDto);
		assertEquals(201, actual.getStatusCodeValue());

	}
}
