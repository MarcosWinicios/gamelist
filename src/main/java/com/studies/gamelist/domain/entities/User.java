package com.studies.gamelist.domain.entities;

import java.io.Serializable;

import com.studies.gamelist.api.dto.input.UserInputDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private String email;
	private String password;
	
	public User(UserInputDTO userInputDTO) {
		this.name =  userInputDTO.getName();
		this.email = userInputDTO.getEmail();
		this.password = userInputDTO.getPassword();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
