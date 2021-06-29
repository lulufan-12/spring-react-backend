package com.ultimate.springreact.model.entities;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

public class InputWorkedHours {
	
	private Integer project;
	@Min(value = 1)
	@Max(value = 24)
	private Integer workedHours;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date date;
	
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
		this.project = project;
	}
	public Integer getWorkedHours() {
		return workedHours;
	}
	public void setWorkedHours(Integer workedHours) {
		this.workedHours = workedHours;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
