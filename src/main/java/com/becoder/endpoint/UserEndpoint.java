package com.becoder.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.dto.PasswordChngRequest;

@RequestMapping("/api/v1/user")
public interface UserEndpoint {

	@GetMapping("/profile")
	public ResponseEntity<?> getProfile();
	
	@PostMapping("/chng-pswd")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChngRequest passwordRequest);
}
