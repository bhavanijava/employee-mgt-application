package com.web.service;

import java.util.*;

import com.web.exception.EmployeeAlreadyExit;
import com.web.exception.EmployeeNotFoundException;
import com.web.exception.EmployeesNotFoundException;
import com.web.model.Employee;

public interface EmployeeService {

	public Employee save(Employee e) throws EmployeeAlreadyExit;
	public List<Employee> findAll() throws EmployeesNotFoundException;
	public Employee getOne(Integer id) throws EmployeeNotFoundException;
	public String delete(Integer id) throws EmployeeNotFoundException;
	public Employee updateEmp(Employee employee,Integer id);
	public List<Employee> saveAllEmployees(List<Employee> employees);
}
