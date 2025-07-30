package com.example.demo.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById_success() {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("Engineering");

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Shashank");
        emp.setDepartment(dept); 

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("Shashank", result.getName());
        assertEquals("Engineering", result.getDepartment().getName());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_notFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(2L);
        });

        assertEquals("Employee not found with id: 2", exception.getMessage());
    }

    @Test
    void testGetAllEmployees() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Alice");

        when(employeeRepository.findAll()).thenReturn(List.of(emp));

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());

        verify(employeeRepository).findAll();
    }

    @Test
    void testSaveEmployee() {
        Employee emp = new Employee();
        emp.setName("Test");

        when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

        Employee result = employeeService.saveEmployee(emp);

        assertNotNull(result);
        assertEquals("Test", result.getName());

        verify(employeeRepository).save(emp);
    }

    @Test
    void testUpdateEmployee_success() {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("Engineering");

        Employee existing = new Employee();
        existing.setId(1L);
        existing.setName("Old Name");
        existing.setEmail("old@example.com");
        existing.setRole("Old Role");
        existing.setDepartment(dept);

        Employee updated = new Employee();
        updated.setName("New Name");
        updated.setEmail("new@example.com");
        updated.setRole("New Role");
        updated.setDepartment(dept);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class))).thenReturn(existing);

        Employee result = employeeService.updateEmployee(1L, updated);

        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        assertEquals("New Role", result.getRole());

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_success() {
        doNothing().when(employeeRepository).deleteById(1L);
        employeeService.deleteEmployee(1L);
        verify(employeeRepository).deleteById(1L);
    }
}
