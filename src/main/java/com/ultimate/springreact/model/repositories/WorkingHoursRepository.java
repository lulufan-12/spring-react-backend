package com.ultimate.springreact.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ultimate.springreact.model.entities.WorkingHours;

public interface WorkingHoursRepository extends CrudRepository<WorkingHours, Integer> {

}
