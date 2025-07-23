package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllEmployees() throws Exception {
        Employee emp = new Employee();
        emp.setName("Alice");
        emp.setRole("Developer");

        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(emp));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee emp = new Employee();
        emp.setName("Bob");
        emp.setRole("Tester");

        Mockito.when(employeeRepository.findById(eq(1L))).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        Department dept = new Department();
        dept.setName("Engineering");

        Employee emp = new Employee();
        emp.setName("Charlie");
        emp.setRole("Manager");
        emp.setDepartment(dept);

        Mockito.when(departmentRepository.findById(eq(1L))).thenReturn(Optional.of(dept));
        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(emp);

        String requestBody = """
            {
                "name": "Charlie",
                "role": "Manager",
                "email": "charlie@example.com",
                "department": {
                    "id": 1
                }
            }
        """;

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"));
    }
}
