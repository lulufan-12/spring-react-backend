package com.ultimate.springreact.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.security.AccountCredentials;
import com.ultimate.springreact.model.security.JwtTokenUtils;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtils jwtTokenUtils;
	
	public AuthenticationController(AuthenticationManager authenticationManager,
			JwtTokenUtils jwtTokenUtils) {
		
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtils = jwtTokenUtils;
	}
	
	@PostMapping
	public ResponseEntity<User> login(@RequestBody AccountCredentials credentials) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
					);
			
			User user = (User) authenticate.getPrincipal();
			
			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, jwtTokenUtils.generateAccessToken(user))
					.body(user);
		}
		catch(BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
