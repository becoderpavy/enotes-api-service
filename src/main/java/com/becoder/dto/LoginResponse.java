package com.becoder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

	private UserRequest user;

	private String token;

}
