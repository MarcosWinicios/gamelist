package com.studies.gamelist.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.studies.gamelist.api.dto.GameListDTO;
import com.studies.gamelist.api.dto.UserResumeDTO;
import com.studies.gamelist.api.dto.input.GameListInputDTO;
import com.studies.gamelist.api.dto.input.UserInputDTO;
import com.studies.gamelist.api.dto.input.UserInputUpdateDTO;
import com.studies.gamelist.domain.service.GameListService;
import com.studies.gamelist.domain.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	GameListService gameListService;

	@GetMapping
	private ResponseEntity<List<UserResumeDTO>> findAll() {
		List<UserResumeDTO> result = this.userService.findAll();

		return ResponseEntity.ok(result);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResumeDTO save(@Valid @RequestBody UserInputDTO userInput) {
		var newUser = this.userService.save(userInput);
		return newUser;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResumeDTO> findById(@PathVariable String userId) {
		
		var result = userService.findById(userId);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserResumeDTO> update(@Valid @PathVariable String userId,
			@RequestBody UserInputUpdateDTO user) {
		
		return ResponseEntity.ok(userService.update(userId, user));
	}

	@GetMapping("/{userId}/gamelist")
	public ResponseEntity<List<GameListDTO>> findListByUser(@PathVariable String userId) {

		List<GameListDTO> result = userService.findListByUserId(userId);

		return ResponseEntity.ok(result);
	}

	@PostMapping("/{userId}/list")
	@ResponseStatus(HttpStatus.CREATED)
	public GameListDTO save(@PathVariable String userId, @RequestBody GameListInputDTO gameListInput) {

		return gameListService.save(userId, gameListInput);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> delete(@PathVariable String userId) {
		
		userService.delete(userId);
		return ResponseEntity.noContent().build();
	}

}
