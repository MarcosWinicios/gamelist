package com.studies.gamelist.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studies.gamelist.api.dto.GameListDTO;
import com.studies.gamelist.api.dto.UserResumeDTO;
import com.studies.gamelist.api.dto.input.UserInputDTO;
import com.studies.gamelist.api.dto.input.UserInputUpdateDTO;
import com.studies.gamelist.domain.entities.GameList;
import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.exception.BusinessException;
import com.studies.gamelist.domain.repository.GameListRepository;
import com.studies.gamelist.domain.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private GameListRepository listRepository;

	@Transactional
	public List<UserResumeDTO> findAll() {

		return repository.findAll().stream().map(user -> new UserResumeDTO(user)).toList();
	}

	@Transactional
	public UserResumeDTO findById(String userId) {

		Optional<User> result = repository.findById(userId);

		if (result.isPresent()) {
			return new UserResumeDTO(result.get());
		}

		return null;
	}

	@Transactional
	public UserResumeDTO save(UserInputDTO userInput) {
		User newUser = new User(userInput);
		User savedUser = repository.save(newUser);
		return new UserResumeDTO(savedUser);
	}

	@Transactional
	public UserResumeDTO update(String userId, UserInputUpdateDTO userInput) {
		verifyId(userId);
		
		var user = new User();
		Optional<User> result = repository.findByEmail(userInput.getEmail());

		if (result.isPresent()) {
			user.setId(userId);
			user.setName(userInput.getName());
			user.setEmail(userInput.getEmail());

			boolean isEquals = result.get().equals(user);

			if (!isEquals) {
				throw new BusinessException("Já existe um usuário com esse email.");
			}

			user = repository.save(user);

			return new UserResumeDTO(user);
		}

		throw new EntityNotFoundException("Usuário inexistente");

	}
	
	@Transactional
	public boolean verifyId(String userId) {
		boolean result = repository.existsById(userId);
		if(!result) {
			throw new EntityNotFoundException("Usuário inexistente");
		}
		
		return result;
	}
	
	@Transactional
	public List<GameListDTO> findListByUserId(String userId) {
		
		List<GameList> result = listRepository.findListByUserId(userId);
		return result.stream().map(list -> new GameListDTO(list)).toList();
	}
	
	@Transactional
	public void delete(String userId) {
		this.verifyId(userId);
		repository.deleteById(userId);
		
	}

}