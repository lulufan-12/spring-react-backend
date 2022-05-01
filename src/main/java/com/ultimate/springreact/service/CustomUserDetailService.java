package com.ultimate.springreact.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ultimate.springreact.model.User; import
com.ultimate.springreact.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = Optional.ofNullable(userRepository.findByEmail(username))
					.orElseThrow( () -> new	UsernameNotFoundException("User not found") );

		return user;
	}
}

