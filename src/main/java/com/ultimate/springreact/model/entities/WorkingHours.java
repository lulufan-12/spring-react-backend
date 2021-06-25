package com.ultimate.springreact.model.entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WorkingHours {
	@Id
	private Integer id;
	@ManyToOne
	private UserProject userProject;
	private Date date;
	private Time startTime;
	private Time stopTime;
	
	public WorkingHours() {
		super();
	}
	
	public WorkingHours(UserProject userProject, Date date, Time startTime, Time stopTime) {
		super();
		this.userProject = userProject;
		this.date = date;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserProject getUserProject() {
		return userProject;
	}

	public void setUserProject(UserProject userProject) {
		this.userProject = userProject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getStopTime() {
		return stopTime;
	}

	public void setStopTime(Time stopTime) {
		this.stopTime = stopTime;
	}
}
