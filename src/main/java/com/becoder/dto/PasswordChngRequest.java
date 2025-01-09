package com.becoder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChngRequest {

	private String oldPassword;
	
	private String newPassword;
	
}
