package com.studies.gamelist.domain.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.gamelist.api.dto.input.UserInputDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank
	@Size(max = 70)
	private String name;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	private String password;
	
	@JsonIgnore
	@OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<GameList> userGameList;
	
	public User(UserInputDTO userInputDTO) {
		this.name =  userInputDTO.getName();
		this.email = userInputDTO.getEmail();
		this.password = userInputDTO.getPassword();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", userGameList="
				+ userGameList + "]";
	}
	
}
