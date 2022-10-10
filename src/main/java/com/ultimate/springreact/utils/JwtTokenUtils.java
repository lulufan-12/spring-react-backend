package com.ultimate.springreact.utils;

import java.io.Serializable;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ultimate.springreact.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@SuppressWarnings("serial")
@Component
public class JwtTokenUtils implements Serializable{
	
	private final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		
		return (String) claims.get("email");
	}
	
	public String getUserId(String token) {
		Claims claims = getAllClaimsFromToken(token);
		
		return (String) claims.get("id");
	}
	
	public String generateAccessToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getName());
		claims.put("name", user.getName());
		claims.put("authorities", user.getAuthorities());
		claims.put("email", user.getEmail());
		
		return Jwts.builder()
				.setSubject(String.format("%s,$s", user.getId(), user.getUsername()))
				.setClaims(claims)
				.signWith(KEY)
				.compact();
	}
	
	public String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().
				setClaims(claims).
				setSubject(subject).
				signWith(KEY).
				compact();
	}
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(KEY)
			.build()
			.parseClaimsJws(token);
			
			return true;
		}
		catch (SignatureException ex) {
			System.err.println("Invalid JWT signature - {}" + ex.getMessage());
		}
		catch (MalformedJwtException ex) {
			System.err.println("Invalid JWT token - {}" + ex.getMessage());
		}
		catch (UnsupportedJwtException ex) {
			System.err.println("Unsupported JWT token - {}" + ex.getMessage());
		}
		catch (IllegalArgumentException ex) {
			System.err.println("JWT claims string is empty - {}" + ex.getMessage());
		}
		return false;
	}

}
