package com.ultimate.springreact.model.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ultimate.springreact.model.entities.User;
import com.ultimate.springreact.model.entities.WorkedHours;

public interface WorkedHoursRepository extends CrudRepository<WorkedHours, Integer> {
	
	@Query("SELECT WH.userProject.project, SUM(WH.quantityHours) FROM WorkedHours WH WHERE WH.userProject.user = :user GROUP BY WH.userProject.project")
	public Iterable<WorkedHours> getTotalHoursByUser(@Param("user") User user);
	
	@Query("SELECT WH.id, WH.userProject.project, WH.userProject.user, SUM(WH.quantityHours) FROM WorkedHours WH GROUP BY WH.userProject.user, WH.userProject.project")
	public Iterable<WorkedHours> getTotalHoursByProject();

	@Query("SELECT WH FROM WorkedHours WH WHERE WH.userProject.user = :user AND WH.date = :date")
	public Iterable<WorkedHours> getAllWorkedHoursByUserAndDate(@Param("user") User user, @Param("date") Date date);
}
