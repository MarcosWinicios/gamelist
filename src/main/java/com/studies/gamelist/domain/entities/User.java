package com.studies.gamelist.domain.entities;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.gamelist.api.dto.input.UserInputDTO;
import com.studies.gamelist.domain.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
public class User{


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

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.MERGE}, mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<GameList> userGameList;

	public User(UserInputDTO userInputDTO) {
		this.name = userInputDTO.getName();
		this.email = userInputDTO.getEmail();
		this.password = userInputDTO.getPassword();
		this.role = userInputDTO.getRole();
	}

	public User(@NotBlank @Size(max = 70) String name, @NotBlank @Email @Size(max = 255) String email, String password,
			UserRole role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	
	public Collection<? extends GrantedAuthority> getAuthorities() {

		if (this.role.equals(UserRole.ADMIN)) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_DEFAULT"));
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_DEFAULT"));
		}
	}

//	@Override
//	public String getUsername() {
//		
//		return email;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//
//		return true;
//	}

}
