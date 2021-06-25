package com.ultimate.springreact.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.springreact.entities.UserProject;

public interface UserProjectRepository extends CrudRepository<UserProject, Integer> {

}
