package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Role is required")
    private String role;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;
    private LocalDate hireDate;
    private String jobId;
    private Double salary;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "department_id")
    private Department department;
}
