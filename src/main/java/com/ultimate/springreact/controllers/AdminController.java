package com.ultimate.springreact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.repositories.UserProjectRepository;

@RestController
@RequestMapping("/admin/api")
public class AdminController {
	
	@Autowired
	UserProjectRepository repository;
	
	@RequestMapping
	public Iterable<UserProject> getUsers(){
		return repository.findAll();
	}
}
