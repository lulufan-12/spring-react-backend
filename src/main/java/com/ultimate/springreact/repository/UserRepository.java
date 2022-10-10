package com.ultimate.springreact.repository;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.springreact.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);

}
