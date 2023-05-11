package com.web.model;




import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee_tab")
@Audited
public class Employee {

	@Id	
	@Min(1)
	@Max(1000)
	private int id;
	
	@NotNull(message="name should not null")
	private String name;
	
	@Min(99)
	@Max(1000)
	private double salary;
	
	@NotBlank(message = "addr should not blank")
	private String addr;
	
	

	
}
