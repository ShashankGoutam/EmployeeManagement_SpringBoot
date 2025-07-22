package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployees();

    
    @Query(value = "SELECT first_name, last_name, email FROM employee", nativeQuery = true)
    List<Object[]> getEmployeeBasicDetails();

    
    @Query(value = "SELECT * FROM employee WHERE salary > ?1", nativeQuery = true)
    List<Employee> findBySalaryGreaterThan(double salary);

    
    @Query(value = "SELECT * FROM employee WHERE hire_date > ?1", nativeQuery = true)
    List<Employee> findEmployeesHiredAfter(LocalDate date);

    
    @Query(value = "SELECT * FROM employee WHERE department_id = ?1", nativeQuery = true)
    List<Employee> findByDepartmentId(int departmentId);

    
    @Query(value = "SELECT COUNT(*) FROM employee", nativeQuery = true)
    long countEmployees();

    
    @Query(value = "SELECT AVG(salary) FROM employee", nativeQuery = true)
    Double findAverageSalary();

    
    @Query(value = "SELECT MAX(salary), MIN(salary) FROM employee", nativeQuery = true)
    Object[] getMaxAndMinSalary();

    
    @Query(value = "SELECT department_id, COUNT(*) FROM employee GROUP BY department_id", nativeQuery = true)
    List<Object[]> getEmployeeCountByDepartment();

    
    @Query(value = "SELECT * FROM employee ORDER BY salary DESC LIMIT 5", nativeQuery = true)
    List<Employee> getTop5Salaries();
}
