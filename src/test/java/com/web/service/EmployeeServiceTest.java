package com.web.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.web.model.Employee;
import com.web.repo.EmployeeRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepo repo;
	
	@Autowired
	private Validator validator;

	
	
	@Test
	public void findAllTest() {
		  List<Employee> employees = Arrays.asList(
		            new Employee(1, "John", 5000.00, "New York"),
		            new Employee(2, "Jane", 6000.00, "Los Angeles")
		        );
		  
		when(repo.findAll()).thenReturn(employees);
		
        List<Employee> result = service.findAll();

		assertEquals(employees, result);
	}
	
	
	@Test
	public void getOneTest() {
	    int id = 1;
	    Employee employee = new Employee(id, "John", 10000.00, "hyd");
	    when(repo.findById(id)).thenReturn(Optional.of(employee));

	    Employee result = service.getOne(id);

	    assertEquals(employee, result);
	}
	
	
	
	
	@Test
	public void deleteTest() {
		int id=999;
		@SuppressWarnings("unused")
		Employee emp = new Employee(id, "Pranya", 33.00, "Pune");
		 service.delete(id);
	     verify(repo, times(1)).deleteById(id);
	}
	
	//positive 
	@Test
	public void saveUserTest() {
		Employee emp = new Employee(999, "bhavani", 200.00, "Pune");
		when(repo.save(emp)).thenReturn(emp);
		assertEquals(emp, service.save(emp));
	}
	
	// Negative
	@Test
	public void saveInvalidData() {
		
	    Employee emp = new Employee(0, null, -888, "");
	    
	    Map<String, String> errors = new HashMap<>();
	    
	    Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
	    
	    for (ConstraintViolation<Employee> violation : violations) {
	        String fieldName = violation.getPropertyPath().toString();
	        String message = violation.getMessage();
	        errors.put(fieldName, message);
	    }
	    
	    if (!errors.isEmpty()) {
	        //System.out.println("Validation errors:");
	        for (Map.Entry<String, String> entry : errors.entrySet()) {
	            System.out.println(" - "+entry.getKey() + ": " + entry.getValue());
	        }
	    }
	}

	
	@Test
	public void updateEmpTest() {


		int id = 123;
        Employee employee = new Employee(id, "John", 10000.00, "hyd");
        Employee updatedEmployee = new Employee(id, "Jane", 15000.00, "pune");
        
        when(repo.findById(id)).thenReturn(Optional.of(employee));
        when(repo.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = service.updateEmp(updatedEmployee, id);

        assertEquals(updatedEmployee, result);
	}
	
	// negative Scenario
	
	@SuppressWarnings("unused")
	@Test
	public void updateEmpInvalidDataTest() {
		int id = 123;
        Employee employee = new Employee(id, "John", 10000.00, "hyd");
        Employee updatedEmployee = new Employee(id, null, -15000.00, "");
        Map<String, String> errors = new HashMap<>();
	    
	    Set<ConstraintViolation<Employee>> violations = validator.validate(updatedEmployee);
	    
	    for (ConstraintViolation<Employee> violation : violations) {
	        String fieldName = violation.getPropertyPath().toString();
	        String message = violation.getMessage();
	        errors.put(fieldName, message);
	    }
	    
	    if (!errors.isEmpty()) {
	        System.out.println("Validation errors:");
	        for (Map.Entry<String, String> entry : errors.entrySet()) {
	            System.out.println(" - "+entry.getKey() + ": " + entry.getValue());
	        }
	    }
	}
	

	
	
}
