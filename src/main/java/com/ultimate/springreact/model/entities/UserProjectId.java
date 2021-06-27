package com.ultimate.springreact.model.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserProjectId implements Serializable {
	private Integer userId;
	private Integer projectId;
	
	public UserProjectId(Integer userId, Integer projectId) {
		super();
		this.userId = userId;
		this.projectId = projectId;
	}
	
	public UserProjectId() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
