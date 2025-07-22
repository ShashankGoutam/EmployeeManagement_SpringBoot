package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 1. Get all employees (full data)
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployees();

    // 2. Get only name and email (basic info)
    @Query(value = "SELECT first_name, last_name, email FROM employee", nativeQuery = true)
    List<Object[]> getEmployeeBasicDetails();

    // 3. Get employees with salary greater than provided value
    @Query(value = "SELECT * FROM employee WHERE salary > ?1", nativeQuery = true)
    List<Employee> findBySalaryGreaterThan(double salary);

    // 4. Get employees hired after a specific date
    @Query(value = "SELECT * FROM employee WHERE hire_date > ?1", nativeQuery = true)
    List<Employee> findEmployeesHiredAfter(LocalDate date);

    // 5. Get employees by department ID
    @Query(value = "SELECT * FROM employee WHERE department_id = ?1", nativeQuery = true)
    List<Employee> findByDepartmentId(int departmentId);

    // 6. Count total employees
    @Query(value = "SELECT COUNT(*) FROM employee", nativeQuery = true)
    long countEmployees();

    // 7. Average salary
    @Query(value = "SELECT AVG(salary) FROM employee", nativeQuery = true)
    Double findAverageSalary();

    // 8. Max and Min salary
    @Query(value = "SELECT MAX(salary), MIN(salary) FROM employee", nativeQuery = true)
    Object[] getMaxAndMinSalary();

    // 9. Count employees per department
    @Query(value = "SELECT department_id, COUNT(*) FROM employee GROUP BY department_id", nativeQuery = true)
    List<Object[]> getEmployeeCountByDepartment();

    // 10. Top 5 highest salaries
    @Query(value = "SELECT * FROM employee ORDER BY salary DESC LIMIT 5", nativeQuery = true)
    List<Employee> getTop5Salaries();
}
