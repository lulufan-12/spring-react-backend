package com.ultimate.springreact.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ultimate.springreact.model.WorkedHours;
import com.ultimate.springreact.repository.WorkedHoursRepository;

@RestController
@RequestMapping("/admin/api")
@AllArgsConstructor
public class AdminController {

	private final WorkedHoursRepository workedHoursRepository;
	
	@GetMapping("/projects")
	public ResponseEntity<Iterable<WorkedHours>> getAllWorkedHours(){
		return ResponseEntity.ok(workedHoursRepository.getTotalHoursByProject());
	}

}
