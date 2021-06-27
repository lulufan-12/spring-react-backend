package com.ultimate.springreact.model.entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WorkedHours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private UserProject userProject;
	private Date date;
	private Time startTime;
	private Time stopTime;
	public WorkedHours(UserProject userProject, Date date, Time startTime, Time stopTime) {
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
