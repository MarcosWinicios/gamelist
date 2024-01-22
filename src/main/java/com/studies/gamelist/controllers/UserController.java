package com.studies.gamelist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.studies.gamelist.dto.UserResumeDTO;
import com.studies.gamelist.dto.input.UserInputDTO;
import com.studies.gamelist.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResumeDTO save(@RequestBody UserInputDTO userInput) {
		System.out.println(userInput.getName());
		
		 var newUser = this.userService.save(userInput);
		
		return newUser;
	}
	
}
