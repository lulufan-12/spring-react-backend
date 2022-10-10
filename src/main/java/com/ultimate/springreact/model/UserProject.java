package com.ultimate.springreact.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserProject {

	@EmbeddedId
	private UserProjectId userProjectId;

	@ManyToOne
	@MapsId("userId")
	private User user;
	
	@ManyToOne
	@MapsId("projectId")
	private Project project;
	
	public UserProject() {
		super();
	}

	public UserProjectId getUserProjectId() {
		return userProjectId;
	}

	public void setUserProjectId(UserProjectId userProjectId) {
		this.userProjectId = userProjectId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
