package com.ultimate.springreact.controllers;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ultimate.springreact.model.entities.InputWorkedHours;
import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.entities.WorkedHours;
import com.ultimate.springreact.model.repositories.UserProjectRepository;
import com.ultimate.springreact.model.repositories.WorkedHoursRepository;
import com.ultimate.springreact.model.services.WorkedHoursService;

@RestController 
@RequestMapping("/api/projects")
public class UserController {
	private WorkedHoursRepository workedHoursRepository;
	private UserProjectRepository userProjectRepository;
	private WorkedHoursService workedHoursService;
	
	@Autowired
	public UserController(UserProjectRepository userProjectRepository,
			WorkedHoursRepository workedHoursRepository,
			WorkedHoursService workedHoursService) {
		
		this.workedHoursRepository = workedHoursRepository;
		this.userProjectRepository = userProjectRepository;
		this.workedHoursService = workedHoursService;
	}
	
	
	@GetMapping
	public ResponseEntity<Iterable<UserProject>> getProjects() {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(userProjectRepository.getUserProjects(user.getId()));
	}

	@GetMapping("/worked-hours")
	public ResponseEntity<Iterable<WorkedHours>> getWorkedHoursByProject() {	
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(workedHoursRepository.getTotalHoursByUser(user));
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerHours(@RequestBody @Valid InputWorkedHours input) throws IOException {
		try {
			workedHoursService.registerWorkedHours(input);
			return ResponseEntity.ok(null);
		}
		catch(Exception error) {
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}
}
