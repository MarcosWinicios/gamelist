package com.studies.gamelist.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studies.gamelist.api.dto.LoginResponseDTO;
import com.studies.gamelist.api.dto.input.AuthenticationLoginDTO;
import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.exception.BusinessException;
import com.studies.gamelist.domain.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;

	public LoginResponseDTO login(AuthenticationLoginDTO inputLogin) {
		LoginResponseDTO loginResponse = new LoginResponseDTO();

		User user = this.loadUserByEmail(inputLogin.getEmail());
		
		System.out.println(user.getName() + ", " + user.getEmail());

		validatePassword(user, inputLogin.getPassword());

		String token = tokenService.generateToken(user);

		loginResponse.setToken(token);

		return loginResponse;
	}
	
	@Transactional
	public User loadUserByEmail(String email) {

//		Optional<User> result = userRepository.findByEmail(email);
		Optional<User> result = userRepository.loadUserByEmail(email);

		if (!result.isPresent()) {
			throw new EntityNotFoundException("Usuário não encontrado");
		}

		return result.get();

	}

	public boolean validatePassword(User user, String passwordValidate) {

		if (!user.getPassword().equals(passwordValidate)) {
			throw new BusinessException("Senha incorreta");
		}
		return true;
	}

}
