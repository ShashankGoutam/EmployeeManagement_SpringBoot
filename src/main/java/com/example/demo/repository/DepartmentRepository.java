package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT * FROM department", nativeQuery = true)
    List<Department> getAllDepartments();

    @Query(value = "SELECT * FROM department WHERE location = ?1", nativeQuery = true)
    List<Department> findByLocation(String location);

    @Query(value = "SELECT COUNT(*) FROM department", nativeQuery = true)
    long countDepartments();
}
