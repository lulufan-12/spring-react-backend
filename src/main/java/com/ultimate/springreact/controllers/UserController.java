package com.ultimate.springreact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository repository;
	
	@RequestMapping
	public Iterable<User> getUsers(){
		return repository.findAll();
	}
}
