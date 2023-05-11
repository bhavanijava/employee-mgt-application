package com.web.service;

import java.util.*;

import com.web.exception.EmployeeNotFoundException;
import com.web.model.Employee;

public interface EmployeeService {

	public Employee save(Employee e);
	public List<Employee> findAll();
	public Employee getOne(Integer id) throws EmployeeNotFoundException;
	public void delete(Integer id);
	public Employee updateEmp(Employee employee,Integer id);
	public List<Employee> saveAllEmployees(List<Employee> employees);
}
