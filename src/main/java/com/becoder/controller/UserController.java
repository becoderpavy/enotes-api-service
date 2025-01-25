package com.becoder.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.PasswordChngRequest;
import com.becoder.dto.UserResponse;
import com.becoder.endpoint.UserEndpoint;
import com.becoder.entity.User;
import com.becoder.service.UserService;
import com.becoder.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController implements UserEndpoint {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<?> getProfile() {
		User loggedInUser = CommonUtil.getLoggedInUser();
		UserResponse userResponse = mapper.map(loggedInUser, UserResponse.class);
		return CommonUtil.createBuildResponse(userResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> changePassword(PasswordChngRequest passwordRequest) {
		userService.changePassword(passwordRequest);
		return CommonUtil.createBuildResponseMessage("Password change success", HttpStatus.OK);
	}

}
