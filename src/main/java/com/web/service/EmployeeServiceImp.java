package com.web.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.exception.EmployeeNotFoundException;
import com.web.model.Employee;
import com.web.repo.EmployeeRepo;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;

	@Override
	public Employee save(Employee e) {
		return repo.save(e);
	}

	@Override
	public List<Employee> findAll() {
		return repo.findAll();
	}

	@Override
	public Employee getOne(Integer id) throws EmployeeNotFoundException {
	    Optional<Employee> optionalEmployee = repo.findById(id);
	    if (optionalEmployee.isPresent()) {
	        return optionalEmployee.get();
	    } else {
	        throw new EmployeeNotFoundException("Employee with id " + id + " not found");
	    }
	}


	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
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
