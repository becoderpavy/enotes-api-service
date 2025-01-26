package com.becoder.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.dto.LoginRequest;
import com.becoder.dto.UserRequest;
import com.becoder.handler.GenericResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Authentication", description = "User Authentication APIs")
@RequestMapping("/api/v1/auth")
public interface AuthEndpoint {

	@Operation(summary ="Register User",description = "Register user",tags = {"Authentication"} )
	@ApiResponses(value = {
			@ApiResponse(responseCode ="201",description = "Success Register"),
			@ApiResponse(responseCode = "500",description = "internal server error",
						 content = @Content(mediaType = "application/json",
						 schema = @Schema(implementation = GenericResponse.class)))})
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userDto, HttpServletRequest request)
			throws Exception;

	@Operation(summary ="Login User",description = "Login user" )
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception;
}
