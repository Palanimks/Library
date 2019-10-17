package com.hcl.lms.service;

<<<<<<< HEAD
import org.springframework.stereotype.Service;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.lms.dto.ResponseDto;
import com.hcl.lms.dto.UserDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.repository.UserRepository;

>>>>>>> Feature3

@Service
public class UserServiceImpl implements UserService {

<<<<<<< HEAD
=======
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseDto addUser(UserDto userDto) {
		User user = new User();
		ResponseDto responseDto = new ResponseDto();
		BeanUtils.copyProperties(userDto, user);
		userRepository.save(user);
		responseDto.setMessage("User created successfully.");
		responseDto.setStatus("Success");
		return responseDto;
	}

>>>>>>> Feature3
}
