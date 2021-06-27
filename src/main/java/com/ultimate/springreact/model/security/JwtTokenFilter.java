package com.ultimate.springreact.model.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ultimate.springreact.model.repositories.UserRepository;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenUtils jwtTokenUtils;
	private final UserRepository repository;
	
	public JwtTokenFilter(JwtTokenUtils jwtTokenUtils, UserRepository repository) {
		this.jwtTokenUtils = jwtTokenUtils;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(header == null || header.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!jwtTokenUtils.validateToken(header)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		UserDetails userDetails = repository.findByEmail(jwtTokenUtils.getUsernameFromToken(header));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails,
				userDetails.getPassword(),
				userDetails.getAuthorities());
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}
