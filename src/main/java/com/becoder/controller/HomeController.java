package com.becoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.PswdResetRequest;
import com.becoder.endpoint.HomeEndpoint;
import com.becoder.service.HomeService;
import com.becoder.service.UserService;
import com.becoder.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController implements HomeEndpoint {

	Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<?> verifyUserAccount(Integer uid, String code) throws Exception {
		log.info("HomeController : verifyUserAccount() : Exceution Start");
		Boolean verifyAccount = homeService.verifyAccount(uid, code);
		if (verifyAccount)
			return CommonUtil.createBuildResponseMessage("Account verification success", HttpStatus.OK);

		log.info("HomeController : verifyUserAccount() : Exceution End");
		return CommonUtil.createErrorResponseMessage("Invalid Verification link", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> sendEmailForPasswordReset(String email, HttpServletRequest request) throws Exception {
		userService.sendEmailPasswordReset(email, request);
		return CommonUtil.createBuildResponseMessage("Email Send Success !! Check Email Reset Password", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> verifyPasswordResetLink(Integer uid, String code) throws Exception {
		userService.verifyPswdResetLink(uid, code);
		return CommonUtil.createBuildResponseMessage("verification success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> resetPassword(PswdResetRequest pswdResetRequest) throws Exception {
		userService.resetPassword(pswdResetRequest);
		return CommonUtil.createBuildResponseMessage("Password reset succes", HttpStatus.OK);
	}

}
