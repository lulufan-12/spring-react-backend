package com.ultimate.springreact.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ultimate.springreact.model.UserProject;
import com.ultimate.springreact.model.key.UserProjectId;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends CrudRepository<UserProject, UserProjectId> {
	
	@Query("SELECT P FROM Project P, UserProject UP WHERE UP.userProjectId.userId = :userId AND P.id = UP.userProjectId.projectId")
	Iterable<UserProject> getUserProjects(@Param("userId") Integer userId);

}
