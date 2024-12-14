package com.becoder.service;

import com.becoder.dto.LoginRequest;
import com.becoder.dto.LoginResponse;
import com.becoder.dto.UserRequest;

public interface AuthService {

	public Boolean register(UserRequest userDto, String url) throws Exception;

	public LoginResponse login(LoginRequest loginRequest);
	
}
