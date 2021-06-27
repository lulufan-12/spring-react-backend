package com.ultimate.springreact.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.springreact.model.entities.UserProject;
import com.ultimate.springreact.model.entities.UserProjectId;

public interface UserProjectRepository extends CrudRepository<UserProject, UserProjectId> {
	
}
