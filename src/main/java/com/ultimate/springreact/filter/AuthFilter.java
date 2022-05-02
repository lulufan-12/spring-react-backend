package com.ultimate.springreact.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultimate.springreact.dto.CredentialsRequest;
import com.ultimate.springreact.model.User;
import com.ultimate.springreact.utils.JwtTokenUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  private final ObjectMapper mapper;
  private final AuthenticationManager manager;

  @SneakyThrows
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    CredentialsRequest credentials = mapper.readValue(request.getInputStream(), CredentialsRequest.class);

    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

    return manager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();
    String json = mapper.writeValueAsString(user);

    response.addHeader(HttpHeaders.AUTHORIZATION, new JwtTokenUtils().generateAccessToken(user));
    response.addHeader("user", json);
  }
}
