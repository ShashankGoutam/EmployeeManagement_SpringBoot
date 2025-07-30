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

    private Employee createMockEmployee(Long id, String name, String role, String email, String deptName) {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName(deptName);

        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setRole(role);
        emp.setEmail(email);
        emp.setDepartment(dept);
        return emp;
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee emp = createMockEmployee(1L, "Alice", "Developer", "alice@example.com", "Engineering");

        Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of(emp));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].department.name").value("Engineering"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee emp = createMockEmployee(2L, "Bob", "Tester", "bob@example.com", "QA");

        Mockito.when(employeeService.getEmployeeById(eq(2L))).thenReturn(emp);

        mockMvc.perform(get("/employees/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.department.name").value("QA"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("Engineering");

        Employee emp = createMockEmployee(3L, "Charlie", "Manager", "charlie@example.com", "Engineering");

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
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("Engineering");

        Employee emp = createMockEmployee(4L, "Updated", "Engineer", "updated@example.com", "Engineering");

        Mockito.when(departmentService.getById(eq(1L))).thenReturn(dept);
        Mockito.when(employeeService.updateEmployee(eq(4L), any(Employee.class))).thenReturn(emp);

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

        mockMvc.perform(put("/employees/4")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.doNothing().when(employeeService).deleteEmployee(5L);

        mockMvc.perform(delete("/employees/5").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeeWithDepartment() throws Exception {
        Employee emp = createMockEmployee(6L, "EmpName", "Analyst", "emp@example.com", "Engineering");

        Mockito.when(employeeService.getEmployeeById(6L)).thenReturn(emp);

        mockMvc.perform(get("/employees/6/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("EmpName"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }
}
