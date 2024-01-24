package com.studies.gamelist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studies.gamelist.dto.UserResumeDTO;
import com.studies.gamelist.dto.input.UserInputDTO;
import com.studies.gamelist.entities.User;
import com.studies.gamelist.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional
	public UserResumeDTO save(UserInputDTO userInput) {
		User newUser = new User(userInput);
		User savedUser = repository.save(newUser);
		return new UserResumeDTO(savedUser);
	}

	public List<UserResumeDTO> findAll() {

		return repository.findAll().stream().map(user -> new UserResumeDTO(user)).toList();
	}

}
