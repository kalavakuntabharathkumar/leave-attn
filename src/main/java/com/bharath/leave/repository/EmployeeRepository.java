package com.bharath.leave.repository;
import com.bharath.leave.model.Employee; import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee,Long>{ boolean existsByEmail(String email); }
