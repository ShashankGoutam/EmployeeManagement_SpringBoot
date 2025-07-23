package com.example.demo.repository;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveAndFindEmployee() {
        // Save department
        Department dept = new Department();
        dept.setName("Engineering");
        departmentRepository.save(dept);

        // Save employee
        Employee emp = new Employee();
        emp.setName("Bob");
        emp.setRole("Tester");
        emp.setEmail("bob@example.com"); // required
        emp.setPhoneNumber("1234567890");
        emp.setHireDate(LocalDate.now());
        emp.setJobId("TEST123");
        emp.setSalary(50000.0);
        emp.setDepartment(dept);

        Employee saved = employeeRepository.save(emp);

        // Fetch employee
        Optional<Employee> found = employeeRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Bob", found.get().getName());
        assertEquals("Engineering", found.get().getDepartment().getName());
    }
}
