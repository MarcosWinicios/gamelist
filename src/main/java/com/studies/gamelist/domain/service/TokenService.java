package com.studies.gamelist.domain.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.studies.gamelist.api.dto.LoginResponseDTO;
import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String TOKEN_ATRIBUTE = "Authorization";
	private static final String ISSUER = "game-list";
	private static final Integer TOKEN_DURATION_TIME = 1;

	@Value("${api.security.token.secret}")
	private String secretKey;

	public TokenService() {
		System.out.println("Entrou no construtor TokenService");
	}

	public LoginResponseDTO generateToken(User user) {

		try {

			Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

			String jwtToken = JWT.create().withIssuer(ISSUER).withSubject(user.getEmail())
					.withExpiresAt(this.generatedExpirationDate()).sign(algorithm);
			
			return new LoginResponseDTO(TOKEN_PREFIX + jwtToken, JWT.decode(jwtToken).getExpiresAtAsInstant(), user.getId());

		} catch (JWTCreationException e) {
			e.printStackTrace();
			throw new JWTCreationException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}

	}

	public String validateToken(String token) {
		DecodedJWT decodedJWT;
		try {

			Algorithm algorithm = Algorithm.HMAC256(secretKey);

			JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
			System.out.println("JWT A SER VERIFICADO: " + verifier.toString());

			decodedJWT = verifier.verify(token);

			System.out.println("JWT Verificado: " + decodedJWT.toString());

			return decodedJWT.getSubject();
		} catch (SignatureVerificationException e) {
			e.printStackTrace();
//			throw new SignatureVerificationException(Algorithm.HMAC256(secretKey));
			throw new BusinessException(e.getMessage());
		} catch (JWTVerificationException e) {
			e.printStackTrace();
//			throw new JWTVerificationException(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	public String recoverToken(HttpServletRequest request) {

		var authorizationHeader = request.getHeader(TOKEN_ATRIBUTE);

		if (authorizationHeader == null) {
			return null;
		}

		if (!authorizationHeader.startsWith(TOKEN_PREFIX)) {
			return null;
		}

		return authorizationHeader.replace(TOKEN_PREFIX, "").trim();

	}

	private Instant generatedExpirationDate() {
		return LocalDateTime.now().plusMinutes(TOKEN_DURATION_TIME).toInstant(ZoneOffset.of("-03:00"));
	}

}
