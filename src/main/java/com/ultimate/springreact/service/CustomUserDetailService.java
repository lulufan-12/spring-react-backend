package com.ultimate.springreact.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ultimate.springreact.model.User;
import com.ultimate.springreact.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = Optional.ofNullable(userRepository.findByEmail(username))
					.orElseThrow( () -> new	UsernameNotFoundException("User not found") );

		return user;
	}

}

