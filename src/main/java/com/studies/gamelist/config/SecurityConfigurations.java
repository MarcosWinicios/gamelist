package com.studies.gamelist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.studies.gamelist.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private JwtFilter jwtFilter;

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
//			.authorizeHttpRequests((authorize) -> {
//				authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//						.requestMatchers(HttpMethod.POST, "/auth/login")
//						.permitAll().requestMatchers(HttpMethod.POST, "/user").permitAll()
//						.requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
//						.requestMatchers(HttpMethod.GET, "/lists").hasRole("ADMIN")
//						.requestMatchers(HttpMethod.GET, "/game").hasRole("DEFAULT")
//						.anyRequest().authenticated();
//			})
//			.headers(headers -> headers.frameOptions().disable())
//			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//		http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		/*Configuração de permissão para o h2 database*/
		http.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
		.authorizeHttpRequests(auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll())
		.headers(headers -> headers.frameOptions().disable());

		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorize) -> {
				authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/lists").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/game").hasRole("DEFAULT")
//						.requestMatchers("/h2-console").permitAll()
						.anyRequest().authenticated();
			}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
