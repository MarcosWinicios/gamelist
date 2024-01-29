package com.studies.gamelist.domain.service;

import java.util.Optional;

import org.antlr.v4.runtime.tree.pattern.TokenTagToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studies.gamelist.api.dto.LoginResponseDTO;
import com.studies.gamelist.api.dto.input.AuthenticationLoginDTO;
import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.repository.UserRepository;
import com.studies.gamelist.security.TokenUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public LoginResponseDTO login(AuthenticationLoginDTO inputLogin) {
		LoginResponseDTO loginResponse = new LoginResponseDTO();
		
		User user = this.loadUserByEmail(inputLogin.getEmail());
		
		String token = TokenUtil.generateToken(user);
		
		loginResponse.setToken(token);
		
		System.out.println("Token Gerado: " + loginResponse.getToken());
		
		return loginResponse;
	}
	
	
	public User loadUserByEmail(String email) {
		
		Optional<User> result =  userRepository.findByEmail(email);
		
		if(!result.isPresent()) {
			throw new EntityNotFoundException("Usuário não encontrado");
		}
		
		return result.get();
		
	}

}
