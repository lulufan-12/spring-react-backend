package com.ultimate.springreact.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
public class WorkedHours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private UserProject userProject;

	@Column(nullable = false)
	@Min(value = 1)
	private Integer quantityHours;

	@Column(nullable = false)	
	private Date date;
	
	public WorkedHours() {
		super();
	}
	
	public WorkedHours(UserProject userProject, Date date, Integer quantityHours) {
		super();
		this.userProject = userProject;
		this.quantityHours = quantityHours;
		this.date = date;
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

	public Integer getQuantityHours() {
		return quantityHours;
	}

	public void setQuantityHours(Integer quantityHours) {
		this.quantityHours = quantityHours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
