package com.ultimate.springreact.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WorkedHoursRequest {
	
	private Integer project;

	@Min(value = 1)
	@Max(value = 24)
	private Integer workedHours;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date date;

}
