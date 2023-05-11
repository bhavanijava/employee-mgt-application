package com.web.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.web.exception.EmployeeNotFoundException;
import com.web.model.Employee;
import com.web.service.EmployeeServiceImp;

// This annotation is used to enable Spring's test framework support
@ExtendWith(SpringExtension.class)

// This annotation indicates that the class is a Spring Boot test and Spring will create a test context for us.
@SpringBootTest
class EmployeeRepositoryTest {

    // This annotation creates a mock instance of EmployeeRepo, which is used in the tests.
    @Mock
    private EmployeeRepo repo;

    // This annotation creates an instance of EmployeeServiceImp and injects the mock repo into it.
    @InjectMocks
    private EmployeeServiceImp service;

    // This test checks whether an employee can be successfully saved.
    @Test
    void testSave() {
        Employee emp = new Employee(1, "John", 1000.00, "London");
        when(repo.save(emp)).thenReturn(emp);
        Employee savedEmp = service.save(emp);
        assertNotNull(savedEmp);
        assertEquals("John", savedEmp.getName());
    }

    // This test checks whether all employees can be successfully retrieved.
    @Test
    void testFindAll() {
        when(repo.findAll()).thenReturn(List.of(new Employee(1, "John", 1000.00, "London"),
                new Employee(2, "Mary", 2000.00, "New York")));
        List<Employee> empList = service.findAll();
        assertNotNull(empList);
        assertEquals(2, empList.size());
    }

    // This test checks whether an employee can be successfully retrieved by its ID.
    @Test
    void testGetOne() throws EmployeeNotFoundException {
        Employee emp = new Employee(1, "John", 1000.00, "London");
        when(repo.findById(1)).thenReturn(Optional.of(emp));
        Employee foundEmp = service.getOne(1);
        assertNotNull(foundEmp);
        assertEquals("John", foundEmp.getName());
    }

    // This test checks whether an employee can be successfully deleted.
    @Test
    void testDelete() {
        Mockito.doNothing().when(repo).deleteById(1);
        service.delete(1);
        assertTrue(true); // no exception thrown
    }

    // This test checks whether an employee's information can be successfully updated.
    @Test
    void testUpdateEmp() {
        Employee emp = new Employee(1, "John", 1000.00, "London");
        when(repo.findById(1)).thenReturn(Optional.of(emp));
        when(repo.save(emp)).thenReturn(emp);
        Employee updatedEmp = service.updateEmp(new Employee(1, "John", 2000.00, "New York"), 1);
        assertNotNull(updatedEmp);
        assertEquals(2000, updatedEmp.getSalary());
        assertFalse("London".equals(updatedEmp.getAddr()));
    }
}
