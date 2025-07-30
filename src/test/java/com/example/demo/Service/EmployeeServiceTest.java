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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

        Employee firstCall = employeeService.getEmployeeById(1L);
        assertEquals("Shashank", firstCall.getName());

        Employee secondCall = employeeService.getEmployeeById(1L);
        assertEquals("Shashank", secondCall.getName());

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
}
