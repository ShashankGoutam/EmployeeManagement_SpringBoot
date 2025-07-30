package com.example.demo.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        // Optional: Clear cache between tests if using Spring's default cache manager
    }

    @Test
    void testGetEmployeeById_success() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Shashank");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

        Employee firstCall = employeeService.getEmployeeById(1L);
        assertEquals("Shashank", firstCall.getName());

        Employee secondCall = employeeService.getEmployeeById(1L);
        assertEquals("Shashank", secondCall.getName());

        // Accept cache call on 1st and none afterward
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