package com.studies.gamelist.api.dto;

import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResumeDTO {
	
	private String id;
	private String name;
	private String email;
	private UserRole role;
	
	public UserResumeDTO(User user) {

		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.role = user.getRole();
	}
	
	
}
