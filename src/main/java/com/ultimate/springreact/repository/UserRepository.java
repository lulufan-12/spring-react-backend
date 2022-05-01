package com.ultimate.springreact.repository;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.springreact.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
