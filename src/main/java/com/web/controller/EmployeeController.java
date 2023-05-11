package com.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.exception.EmployeeNotFoundException;
import com.web.model.Employee;
import com.web.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@PostMapping("/employee")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
	    Employee savedEmployee = service.save(employee);
	    return savedEmployee;
	}



	
	@PutMapping("/update/{id}")
	public Employee update(@RequestBody Employee ed,@PathVariable Integer id)
	{
		return service.updateEmp(ed, id);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<List<Employee>> saveEmployees(@Valid @RequestBody List<Employee> employees) {
	    List<Employee> savedEmployees = service.saveAllEmployees(employees);
	    return ResponseEntity.ok(savedEmployees);
	}

	@GetMapping("/findAll")
	public List<Employee> findAllEmps()
	{
		return service.findAll();
	}
	
	@GetMapping("findOne/{id}")
	public Employee findOne(@PathVariable Integer id) throws EmployeeNotFoundException
	{
		return service.getOne(id);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
	    try {
	       service.delete(id);
	        return new ResponseEntity<>("Employee with ID " + id + " has been deleted", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to delete employee with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
}
