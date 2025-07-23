package com.example.demo.exception;

import com.example.demo.controller.EmployeeController;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository; 

    @Test
    void testResourceNotFoundExceptionHandling() throws Exception {
        Mockito.when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/employees/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Employee not found with id: 999"))
                .andExpect(jsonPath("$.status").value(404));
    }
}
