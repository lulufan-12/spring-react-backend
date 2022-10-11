package com.ultimate.springreact.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ultimate.springreact.dto.WorkedHoursRequest;
import com.ultimate.springreact.model.User;
import com.ultimate.springreact.model.UserProject;
import com.ultimate.springreact.model.key.UserProjectId;
import com.ultimate.springreact.model.WorkedHours;
import com.ultimate.springreact.repository.WorkedHoursRepository;

@Service
@AllArgsConstructor
public class WorkedHoursService {

	private WorkedHoursRepository workedHoursRepository;
	
	public void registerWorkedHours(WorkedHoursRequest input) throws Exception {
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

		WorkedHours hours = new WorkedHours();
		hours.setUserProject(userProject);
		hours.setDate(sqlDate);
		hours.setQuantityHours(input.getWorkedHours());

		workedHoursRepository.save(hours);
	}

}
