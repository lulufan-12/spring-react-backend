package com.ultimate.springreact.controller;

import java.io.IOException;
import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ultimate.springreact.dto.WorkedHoursRequest;
import com.ultimate.springreact.model.User;
import com.ultimate.springreact.model.UserProject;
import com.ultimate.springreact.model.WorkedHours;
import com.ultimate.springreact.repository.UserProjectRepository;
import com.ultimate.springreact.repository.WorkedHoursRepository;
import com.ultimate.springreact.service.WorkedHoursService;

@RestController 
@RequestMapping("/api/projects")
@AllArgsConstructor
public class UserController {

	private final WorkedHoursRepository workedHoursRepository;
	private final UserProjectRepository userProjectRepository;
	private final WorkedHoursService workedHoursService;
	
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
	public ResponseEntity<Object> registerHours(@RequestBody @Valid WorkedHoursRequest input) throws IOException {
		try {
			workedHoursService.registerWorkedHours(input);
			return ResponseEntity.ok(null);
		}
		catch(Exception error) {
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}

}
