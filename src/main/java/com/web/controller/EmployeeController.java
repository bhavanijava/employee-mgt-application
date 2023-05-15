package com.web.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.exception.EmployeeAlreadyExit;
import com.web.exception.EmployeeNotFoundException;
import com.web.exception.EmployeesNotFoundException;
import com.web.model.Employee;
import com.web.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@PostMapping("/employee")
	public Employee createEmployee(@Valid @RequestBody Employee employee) throws EmployeeAlreadyExit {
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
	public List<Employee> findAllEmps() throws EmployeesNotFoundException
	{
		return service.findAll();
	}
	
	@GetMapping("findOne/{id}")
	public Employee findOne(@PathVariable Integer id) throws EmployeeNotFoundException {
		log.info("EmployeeController: findOne request body {}", id);
        Employee employee = service.getOne(id);
        log.info("EmployeeController: findOne response body {}", employee);
        return employee;
	}

	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable Integer id) throws EmployeeNotFoundException {
		return service.delete(id);
	}

	
	
}
