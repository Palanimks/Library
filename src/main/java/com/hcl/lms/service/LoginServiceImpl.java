package com.hcl.lms.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.lms.dto.LoginRequestDto;
import com.hcl.lms.dto.LoginResponseDto;
import com.hcl.lms.entity.User;
import com.hcl.lms.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		LoginResponseDto loginResponseDto = null;

		Optional<User> user = userRepository.findByEmailAndPassword(loginRequestDto.getEmail(),
				loginRequestDto.getPassword());

		if (user.isPresent()) {
			loginResponseDto = new LoginResponseDto();
			loginResponseDto.setMessage("Login successfully.");
			loginResponseDto.setStatus("Success");
			loginResponseDto.setFirstName(user.get().getFirstName());
			loginResponseDto.setUserId(user.get().getUserId());
		}

		return loginResponseDto;
	}

}
