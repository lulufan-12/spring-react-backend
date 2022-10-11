package com.ultimate.springreact.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultimate.springreact.SpringReactBackendApplication;
import com.ultimate.springreact.dto.CredentialsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringReactBackendApplication.class)
@AutoConfigureMockMvc
public class AuthenticationTest {

  @Autowired
  MockMvc mockMvc;

  @Spy
  private ObjectMapper mapper;

  @Test
  public void loginSuccess() throws Exception {
    CredentialsRequest credentials = new CredentialsRequest();
    credentials.setUsername("p1@teste.com");
    credentials.setPassword("teste123");

    String json = mapper.writeValueAsString(credentials);

    mockMvc.perform(
            post("/v2/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
    )
        .andExpect(status().isOk())
        .andExpect(header().exists("user"))
        .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
  }

  @Test
  public void loginFailure() throws Exception {
    CredentialsRequest credentials = new CredentialsRequest();
    credentials.setUsername("other@teste.com");
    credentials.setPassword("teste123");

    String json = mapper.writeValueAsString(credentials);

    mockMvc.perform(
            post("/v2/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
    )
            .andExpect(status().isUnauthorized())
            .andExpect(header().doesNotExist("user"))
            .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION));
  }
}
