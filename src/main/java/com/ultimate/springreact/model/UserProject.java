package com.ultimate.springreact.model;

import com.ultimate.springreact.model.key.UserProjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserProject {

	@EmbeddedId
	private UserProjectId userProjectId;

	@ManyToOne
	@MapsId("userId")
	private User user;
	
	@ManyToOne
	@MapsId("projectId")
	private Project project;

}
