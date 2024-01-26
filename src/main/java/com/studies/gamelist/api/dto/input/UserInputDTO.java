package com.studies.gamelist.api.dto.input;

import com.studies.gamelist.domain.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputDTO {
	

	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	private UserRole role;
}
