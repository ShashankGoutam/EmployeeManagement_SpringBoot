package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@WithMockUser
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Department getMockDepartment() {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("Engineering");
        return dept;
    }

    private Employee getMockEmployee(String name, String role) {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName(name);
        emp.setRole(role);
        emp.setEmail(name.toLowerCase() + "@example.com");
        emp.setDepartment(getMockDepartment());
        return emp;
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of(getMockEmployee("Alice", "Developer")));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].department.name").value("Engineering"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployeeById(eq(1L))).thenReturn(getMockEmployee("Bob", "Tester"));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        Department dept = getMockDepartment();
        Employee emp = getMockEmployee("Charlie", "Manager");

        Mockito.when(departmentService.getById(eq(1L))).thenReturn(dept);
        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(emp);

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
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Department dept = getMockDepartment();
        Employee emp = getMockEmployee("Updated", "Engineer");

        Mockito.when(departmentService.getById(eq(1L))).thenReturn(dept);
        Mockito.when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(emp);

        String requestBody = """
            {
              "name": "Updated",
              "role": "Engineer",
              "email": "updated@example.com",
              "department": {
                "id": 1
              }
            }
        """;

        mockMvc.perform(put("/employees/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeeWithDepartment() throws Exception {
        Employee emp = getMockEmployee("EmpName", "Tester");

        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(emp);

        mockMvc.perform(get("/employees/1/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("EmpName"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }
}
