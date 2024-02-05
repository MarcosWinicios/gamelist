package com.studies.gamelist.security;

import org.springframework.stereotype.Component;

import com.studies.gamelist.domain.entities.User;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class TokenUtil {

	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String TOKEN_ATRIBUTE = "Authorization";
	
	public TokenUtil() {
		System.out.println("Entrou no construtor TokenUtil");
	}
	
	public String generateToken(User user) {

		var jwt = user.getEmail();
		var token = "Bearer " + jwt;

		return token;

	}

	public boolean validateToken(String token, User user) {
		String jwt = user.getEmail();
		
		if (token.equals(jwt)) {
			return true;
		}

		return false;
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
