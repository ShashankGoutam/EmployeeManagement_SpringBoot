package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Cacheable(value = "employee", key = "#id")
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Caching(evict = {
            @CacheEvict(value = "employees", allEntries = true),
            @CacheEvict(value = "employee", key = "#id")
    })
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existing.setName(updatedEmployee.getName());
        existing.setRole(updatedEmployee.getRole());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existing.setHireDate(updatedEmployee.getHireDate());
        existing.setJobId(updatedEmployee.getJobId());
        existing.setSalary(updatedEmployee.getSalary());
        existing.setDepartment(updatedEmployee.getDepartment());

        return repository.save(existing);
    }

    @Caching(evict = {
            @CacheEvict(value = "employees", allEntries = true),
            @CacheEvict(value = "employee", key = "#id")
    })
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "employees", allEntries = true)
    })
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }
}
