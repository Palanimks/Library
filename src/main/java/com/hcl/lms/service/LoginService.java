package com.hcl.lms.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.lms.dto.LoginRequestDto;
import com.hcl.lms.dto.LoginResponseDto;

public interface LoginService {
	LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto);

}
