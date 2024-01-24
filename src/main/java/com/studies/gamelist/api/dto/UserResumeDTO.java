package com.studies.gamelist.api.dto;

import com.studies.gamelist.domain.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResumeDTO {
	
	private String id;
	private String name;
	private String email;
	
	public UserResumeDTO(User user) {

		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
	
	
}
