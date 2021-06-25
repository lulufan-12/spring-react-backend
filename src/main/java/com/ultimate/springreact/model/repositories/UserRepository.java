package com.ultimate.springreact.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.springreact.model.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
