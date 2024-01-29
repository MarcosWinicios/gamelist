package com.studies.gamelist.security;

import org.springframework.stereotype.Component;

import com.studies.gamelist.domain.entities.User;

@Component
public class TokenUtil {
	
	private static final String TOKEN_PREFIX = "Bearer ";
	
	
	public static String generateToken(User user) {
		
		var jwt = "marcos123";
		var token = TOKEN_PREFIX +  " " + jwt;
		
		return token;
		
	}
}
