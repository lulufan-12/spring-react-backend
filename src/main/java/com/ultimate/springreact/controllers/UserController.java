package com.ultimate.springreact.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.repositories.UserProjectRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserProjectRepository repository;
	
	@RequestMapping
	public Iterable<UserProject> getUserProjects(){
		return repository.findAll();
	}

}
