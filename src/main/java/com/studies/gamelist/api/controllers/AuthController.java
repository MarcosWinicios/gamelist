package com.studies.gamelist.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studies.gamelist.api.dto.LoginResponseDTO;
import com.studies.gamelist.api.dto.input.AuthenticationLoginDTO;
import com.studies.gamelist.domain.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationLoginDTO loginData){
		
		System.out.println("Input: " + loginData.getEmail());
		
		var response = authService.login(loginData);
		
		return ResponseEntity.ok(response);
	}
	
}
