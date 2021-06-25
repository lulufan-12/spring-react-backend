package com.ultimate.springreact.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@OneToMany(mappedBy = "user")
	private Set<UserProject> userProjects;
	
	public Project() {
		super();
	}
	
	public Project(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserProject> getUserProjects() {
		return userProjects;
	}

	public void setUserProjects(Set<UserProject> userProjects) {
		this.userProjects = userProjects;
	}
}
