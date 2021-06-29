package com.ultimate.springreact.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ultimate.springreact.model.entities.InputWorkedHours;
import com.ultimate.springreact.model.entities.Project;
import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.entities.UserProjectId;
import com.ultimate.springreact.model.entities.WorkedHours;
import com.ultimate.springreact.model.repositories.ProjectRepository;
import com.ultimate.springreact.model.repositories.UserProjectRepository;
import com.ultimate.springreact.model.repositories.WorkedHoursRepository;

@RestController
@RequestMapping("/api/projects")
public class UserController {
	
	@Autowired
	private WorkedHoursRepository workedHoursRepository;
	@Autowired
	private UserProjectRepository userProjectRepository;
	
	@GetMapping
	public Iterable<UserProject> getProjects() {	
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return userProjectRepository.getUserProjects(user.getId());
	}

	@GetMapping("/worked-hours")
	public Iterable<WorkedHours> getWorkedHoursByProject() {	
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return workedHoursRepository.getTotalHoursByUser(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity registerHours(@RequestBody @Valid InputWorkedHours input) throws IOException {
		
		if(input.getDate().compareTo(Calendar.getInstance().getTime()) > 0) {
			return new ResponseEntity("Unable to log worked hours in the future.", HttpStatus.BAD_REQUEST);
		}
		Date sqlDate = new Date(input.getDate().getTime());
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		
		Iterable<WorkedHours> whs = workedHoursRepository.getAllWorkedHoursByUserAndDate(user, sqlDate);
		long quantityWorkedHours = StreamSupport.stream(whs.spliterator(), false).count();
		
		if(quantityWorkedHours != 0) {
			return new ResponseEntity("Worked hours were already registered for this date.", HttpStatus.BAD_REQUEST);
		}
		
		UserProjectId userProjectId = new UserProjectId(user.getId(), input.getProject());
		UserProject userProject = new UserProject();
		userProject.setUserProjectId(userProjectId);
		WorkedHours hours = new WorkedHours(userProject, sqlDate, input.getWorkedHours());
		workedHoursRepository.save(hours);
		return null;
	}
}
