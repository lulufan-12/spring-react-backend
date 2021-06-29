package com.ultimate.springreact.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ultimate.springreact.model.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
}
