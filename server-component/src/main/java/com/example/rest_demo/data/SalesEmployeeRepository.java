package com.example.rest_demo.data;

import com.example.rest_demo.model.SalesEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesEmployeeRepository extends JpaRepository<SalesEmployee, Long> {
}
