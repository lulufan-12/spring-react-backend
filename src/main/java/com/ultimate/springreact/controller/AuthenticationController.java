package com.ultimate.springreact.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ultimate.springreact.model.User;
import com.ultimate.springreact.dto.CredentialsRequest;
import com.ultimate.springreact.utils.JwtTokenUtils;

@RestController
@RequestMapping("/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtils jwtTokenUtils;

	@GetMapping
	public ResponseEntity<User> getUserByToken() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> login(@RequestBody CredentialsRequest credentials) {
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
