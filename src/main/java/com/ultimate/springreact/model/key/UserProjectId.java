package com.ultimate.springreact.model.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class UserProjectId implements Serializable {

	private Integer userId;
	private Integer projectId;

}
