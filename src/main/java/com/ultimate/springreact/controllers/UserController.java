package com.ultimate.springreact.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.entities.UserProjectId;
import com.ultimate.springreact.model.entities.WorkedHours;
import com.ultimate.springreact.model.repositories.WorkedHoursRepository;

@RestController
@RequestMapping("/api/projects")
public class UserController {
	
	@Autowired
	private WorkedHoursRepository workedHoursRepository;

	@GetMapping("/worked-hours")
	public Iterable<WorkedHours> getWorkedHoursByProject() {	
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return workedHoursRepository.getTotalHoursByUser(user);
	}
	
	@PostMapping("/register")
	public Object registerHours(@RequestParam @JsonFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam Integer quantityHours, 
		@RequestParam Integer project, HttpServletResponse response) throws IOException {
		
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		
		if(workedHoursRepository.getAllWorkedHoursByUserAndDate(user, date) != null) {
			response.setStatus(400);
			PrintWriter printWriter = response.getWriter();
			printWriter.println("Unable to register hours for this date.");
			return null;
		}
		
		UserProjectId userProjectId = new UserProjectId(user.getId(), project);
		
		UserProject userProject = new UserProject();
		
		userProject.setUserProjectId(userProjectId);
		
		WorkedHours workedHours = new WorkedHours(userProject, date, quantityHours);
		
		workedHoursRepository.save(workedHours);
		
		return null;
	}
}
