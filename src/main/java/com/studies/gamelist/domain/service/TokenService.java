package com.studies.gamelist.domain.service;

import org.springframework.stereotype.Service;

import com.studies.gamelist.domain.entities.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String TOKEN_ATRIBUTE = "Authorization";
	
	
	public TokenService() {
		System.out.println("Entrou no construtor TokenService");
	}
	
	
	
	public String generateToken(User user) {

		var jwt = user.getEmail();
		var token = "Bearer " + jwt;

		return token;

	}

	public String validateToken(String token) {
		String jwt = "carlos@gmail.com";
		
		if (token.equals(jwt)) {
			
			System.out.println("Passou");
			return token;
		}

		return "";
	}

	public String recoverToken(HttpServletRequest request) {

		var authorizationHeader = request.getHeader(TOKEN_ATRIBUTE);
		

		if (authorizationHeader == null) {
			return null;
		}
		
		if(!authorizationHeader.startsWith(TOKEN_PREFIX)) {
			return null;
		}
		

		return authorizationHeader.replace(TOKEN_PREFIX, "").trim();

	}


}
