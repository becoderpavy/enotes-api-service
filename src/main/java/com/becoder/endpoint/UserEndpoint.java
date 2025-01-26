package com.becoder.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.dto.PasswordChngRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "Authentication User Operation APIs")
@RequestMapping("/api/v1/user")
public interface UserEndpoint {

	@Operation(summary = "Get User Profile", tags = { "Notes" }, description = "Get User Profile")
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile();

	@Operation(summary = "User Account Password Change", tags = {
			"Notes" }, description = "User Account Password Change")
	@PostMapping("/chng-pswd")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChngRequest passwordRequest);
}
