package com.web.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.exception.EmployeeAlreadyExit;
import com.web.exception.EmployeeNotFoundException;
import com.web.exception.EmployeesNotFoundException;
import com.web.model.Employee;
import com.web.repo.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeRepo repo;

	@Override
	public Employee save(Employee e) throws EmployeeAlreadyExit {
		Optional<Employee> optionalEmployee = repo.findById(e.getId());
		if (optionalEmployee.isPresent()) {
			throw new EmployeeAlreadyExit("Employee with ID " + e.getId() + " already exists!");
		}
		return repo.save(e);
	}


	@Override
	public List<Employee> findAll() throws EmployeesNotFoundException {
		List<Employee> employees = repo.findAll();
		if (employees.isEmpty()) {
			throw new EmployeesNotFoundException("No employees found");
		}
		return employees;
	}

	@Override
	public Employee getOne(Integer id) throws EmployeeNotFoundException {
		try {
			log.info("EmployeeServiceIMp : getOne execution started.");
			Optional<Employee> optionalEmployee = repo.findById(id);
			if (optionalEmployee.isPresent()) {
				Employee employee = optionalEmployee.get();
				log.debug("EmployeeServiceIMP : getOne retrieving employee from database for id {} {}",  id, employee);
				return employee;
			} else {
				throw new EmployeeNotFoundException("Employee with id " + id + " not found");
			}
		} catch (EmployeeNotFoundException e) {
			log.error("EmployeeServiceIMp : getOne Exception occurred: {}", e.getMessage());
			throw e; // re-throw the exception after logging the error message
		}
		finally {
			log.info("EmployeeServiceIMP : getOne execution ended.");
		}
	}




	@Override
	public String delete(Integer id) throws EmployeeNotFoundException {
		Optional<Employee> optionalEmployee = repo.findById(id);
		if (optionalEmployee.isPresent()) {
			repo.deleteById(id);
			return "Employee with id " + id + " has been successfully deleted.";
		} else {
			throw new EmployeeNotFoundException("Employee with id " + id + " not found and try again to delete.");
		}
	}


	@Override
	public Employee updateEmp(Employee employee, Integer id) {
		Optional<Employee> optional=repo.findById(id);
		Employee e1=optional.get();
		e1.setName(employee.getName());
		e1.setSalary(employee.getSalary());
		e1.setAddr(employee.getAddr());
		return repo.save(e1);
	}

	@Override
	public List<Employee> saveAllEmployees(List<Employee> employees) {
		return repo.saveAll(employees);
	}

}
