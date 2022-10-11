package com.ultimate.springreact.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WorkedHours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private UserProject userProject;

	@Column(nullable = false)
	@Min(value = 1)
	private Integer quantityHours;

	@Column(nullable = false)
	private Date date;

}
