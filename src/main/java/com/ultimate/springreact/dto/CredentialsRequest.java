package com.ultimate.springreact.dto;

public class CredentialsRequest {
	
	private String username;
	private String password;
	
	public CredentialsRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

  public CredentialsRequest() {
    super();
  }

  public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}