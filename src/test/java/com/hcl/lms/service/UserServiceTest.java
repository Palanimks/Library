package com.hcl.lms.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.dto.UserDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	@MockBean
	UserRepository userRepository;

	UserDto userDto = null;
	ResponseDto responseDto = null;

	@Test
	public void addUserRegistrationTest() {
		userDto = new UserDto();
		userDto.setEmail("abc@gmail.com");
		userDto.setFirstName("vijay");
		userDto.setLastName("kumar");
		userDto.setMobileNumber(32333l);

		responseDto = new ResponseDto();
		responseDto.setMessage("success....");
		responseDto.setStatus("sure");

		Mockito.when(userRepository.save(Mockito.any())).thenReturn(Mockito.any());
		ResponseDto actualResponseDto=userService.addUser(userDto);

		assertEquals("Success", actualResponseDto.getStatus());
	}

}
