package com.ultimate.springreact.model.services;

import java.sql.Date;
import java.util.Calendar;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ultimate.springreact.model.entities.InputWorkedHours;
import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.entities.UserProjectId;
import com.ultimate.springreact.model.entities.WorkedHours;
import com.ultimate.springreact.model.repositories.WorkedHoursRepository;

@Service
public class WorkedHoursService {
	
	
	private WorkedHoursRepository workedHoursRepository;
	
	@Autowired
	public WorkedHoursService(WorkedHoursRepository workedHoursRepository) {
		this.workedHoursRepository = workedHoursRepository;
	}
	
	public void registerWorkedHours(InputWorkedHours input) throws Exception {
		
		if(input.getDate().compareTo(Calendar.getInstance().getTime()) > 0) {
			throw new Exception("Unable to log worked hours in the future.");
		}
		
		Date sqlDate = new Date(input.getDate().getTime());
		User user = (User) SecurityContextHolder.getContext()
										.getAuthentication()
										.getPrincipal();
		
		Iterable<WorkedHours> whs = workedHoursRepository.getAllWorkedHoursByUserAndDate(user, sqlDate);
		long quantityWorkedHours = StreamSupport.stream(whs.spliterator(), false).count();
		
		if(quantityWorkedHours != 0) {
			throw new Exception("Worked hours were already registered for this date.");
		}
		
		UserProjectId userProjectId = new UserProjectId(user.getId(), input.getProject());
		UserProject userProject = new UserProject();
		userProject.setUserProjectId(userProjectId);
		WorkedHours hours = new WorkedHours(userProject, sqlDate, input.getWorkedHours());
		workedHoursRepository.save(hours);
	}
}
