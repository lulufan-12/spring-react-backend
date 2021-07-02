package com.ultimate.springreact.model.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ultimate.springreact.model.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
}
