package com.studies.gamelist.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import com.studies.gamelist.domain.entities.User;
import com.studies.gamelist.domain.enums.UserRole;
import com.studies.gamelist.domain.repository.UserRepository;
import com.studies.gamelist.domain.service.AuthService;
import com.studies.gamelist.domain.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Entrou no filtro");

//		tokenService = new TokenService();
		var token = tokenService.recoverToken(request);

		if (token != null) {
			System.out.println("TOKEN RECUPERADO: " + token);

			var subject = tokenService.validateToken(token);

			System.out.println("TOKEN VALIDADO");

			UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(subject);

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		} else {
			System.out.println("Authorization null");
		}

		filterChain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(String subject) {

		User user = extractUser(subject);

		return new UsernamePasswordAuthenticationToken(user, user.getEmail(), user.getAuthorities());
	}

	private User extractUser(String subject) {

		Optional<User> result = userRepository.loadUserByEmail(subject);

//		return this.authService.loadUserByEmail(subject);

		if (!result.isPresent()) {
			return null;
		}

		return result.get();
	}

}
