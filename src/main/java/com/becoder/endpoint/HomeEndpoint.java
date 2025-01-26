package com.becoder.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.becoder.dto.PswdResetRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Home", description = "All the Home APIs")
@RequestMapping("/api/v1/home")
public interface HomeEndpoint {

	@Operation(summary = "Verification user account", tags = {
			"Home" }, description = "User Account verification after register account")
	@GetMapping("/verify")
	public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid, @RequestParam String code) throws Exception;

	@Operation(summary = "Send Email for Password Reset", tags = {
			"Home" }, description = "User Can send Email for password reset")
	@GetMapping("/send-email-reset")
	public ResponseEntity<?> sendEmailForPasswordReset(@RequestParam String email, HttpServletRequest request)
			throws Exception;

	@Operation(summary = "Verification password link", tags = {
			"Home" }, description = "User verification password link")
	@GetMapping("/verify-pswd-link")
	public ResponseEntity<?> verifyPasswordResetLink(@RequestParam Integer uid, @RequestParam String code)
			throws Exception;

	@Operation(summary = "Reset Password", tags = { "Home" }, description = "User Can changes Password")
	@PostMapping("/reset-pswd")
	public ResponseEntity<?> resetPassword(@RequestBody PswdResetRequest pswdResetRequest) throws Exception;
}
