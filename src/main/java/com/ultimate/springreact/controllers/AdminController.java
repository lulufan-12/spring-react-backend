package com.ultimate.springreact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ultimate.springreact.model.entities.WorkedHours;
import com.ultimate.springreact.model.repositories.WorkedHoursRepository;

@RestController
@RequestMapping("/admin/api")
public class AdminController {
	private WorkedHoursRepository workedHoursRepository;
	
	@Autowired
	public AdminController(WorkedHoursRepository workedHoursRepository) {
		this.workedHoursRepository = workedHoursRepository;
	}
	
	@GetMapping("/projects")
	public ResponseEntity<Iterable<WorkedHours>> getAllWorkedHours(){	
		
		return ResponseEntity.ok(workedHoursRepository.getTotalHoursByProject());
	}
}
