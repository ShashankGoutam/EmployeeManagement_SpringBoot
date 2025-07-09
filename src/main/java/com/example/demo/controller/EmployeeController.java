package com.example.demo.controller;
import com.example.demo.repository.DepartmentRepository;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private DepartmentRepository DepartmentRepository;


    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        Long deptId = employee.getDepartment().getId();
        Department department = DepartmentRepository.findById(deptId).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + deptId));
    
        employee.setDepartment(department);
        return repository.save(employee);
}


    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        existing.setName(employee.getName());
        existing.setRole(employee.getRole());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}/details")
    public Employee getEmployeeWithDepartment(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

}
