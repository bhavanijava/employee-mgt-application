package com.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.web.exception.EmployeeNotFoundException;
import com.web.model.Employee;
import com.web.service.EmployeeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeControllerTest {

	@Autowired
	private EmployeeController controller;
	
	@MockBean
    private EmployeeService service;

    @Test
    public void testFindAllEmps() {
        // create a list of employees to be returned by the service
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", 5000.00, "New York"),
            new Employee(2, "Jane", 6000.00, "Los Angeles")
        );
        // mock the service to return the list of employees
        when(service.findAll()).thenReturn(employees);
        // call the controller method
        List<Employee> result = controller.findAllEmps();
        // assert that the result matches the expected list of employees
        assertEquals(employees, result);
    }
    
    @Test
    public void testFindOne() throws EmployeeNotFoundException {
        // create an employee to be returned by the service
        Employee employee = new Employee(1, "John", 5000.00, "New York");
        // mock the service to return the employee
        when(service.getOne(1)).thenReturn(employee);
        // call the controller method
        Employee result = controller.findOne(1);
        // assert that the result matches the expected employee
        assertEquals(employee, result);
    }
    
    
    /**

    This method tests the deleteEmployee method of the EmployeeController by verifying
    that the delete method of the EmployeeService is called with the correct employee ID.
    */
    @Test
    public void deleteTest() {
    // set up test data
    int id = 999;
    // call the method being tested
    controller.deleteEmployee(id);
    // verify that the service method was called with the correct ID
    verify(service, times(1)).delete(id);
    }
    
    
    
    @Test
    public void testSaveEmployee() {
        // create a new employee to be saved
        Employee employee = new Employee(1, "John", 5000.00, "New York");
        // mock the service to return the saved employee
        when(service.save(employee)).thenReturn(employee);
        // call the controller method
        Employee result = controller.createEmployee(employee);
        // assert that the result matches the expected employee
        assertEquals(employee, result);
    }

    
    
    /**
     * Tests the update method of the EmployeeController class.
     * Expects the updated employee to match the expected employee with the same ID.
     */
    @Test
    public void testUpdateEmployee() throws Exception {
        // Initializing test data
        int id = 1;
        @SuppressWarnings("unused")
		Employee employee = new Employee(id, "John", 10000.00, "hyd");
        Employee updatedEmployee = new Employee(id, "Jane", 15000.00, "pune");
        
        // Mocking the service layer behavior
        when(service.updateEmp(updatedEmployee, id)).thenReturn(updatedEmployee);
        
        // Invoking the controller method to update the employee
        Employee result=controller.update(updatedEmployee, id);
        
        // Verifying the result
        System.out.println(result);  // printing the result for debugging purpose
        assertEquals(updatedEmployee, result);  // ensuring that the updated employee matches the expected result
    }



}
