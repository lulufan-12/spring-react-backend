package com.ultimate.springreact.repository;

import org.springframework.data.repository.CrudRepository;
import com.ultimate.springreact.model.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
}
