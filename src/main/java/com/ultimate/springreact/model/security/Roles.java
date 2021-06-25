package com.ultimate.springreact.model.security;

public class Roles {
	private static final String[] ROLE_USER = {"ROLE_USER"};
	private static final String[] ROLE_ADMIN = {"ROLE_USER", "ROLE_ADMIN"};
	
	public static String[] getRole(String role) {
		if(role.equals("USER"))
			return ROLE_USER;
		else if(role.equals("ADMIN"))
			return ROLE_ADMIN;
		else
			return null;
	}
}
