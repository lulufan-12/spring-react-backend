package com.ultimate.springreact.repository;

import org.springframework.data.repository.CrudRepository;
import com.ultimate.springreact.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
}
