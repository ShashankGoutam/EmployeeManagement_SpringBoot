package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // 1. Get all departments
    @Query(value = "SELECT * FROM department", nativeQuery = true)
    List<Department> getAllDepartments();

    // 2. Get department by city
    @Query(value = "SELECT * FROM department WHERE location = ?1", nativeQuery = true)
    List<Department> findByLocation(String location);

    // 3. Count departments
    @Query(value = "SELECT COUNT(*) FROM department", nativeQuery = true)
    long countDepartments();
}
