package com.example.rest_demo.controllers;

import com.example.rest_demo.data.SalesEmployeeRepository;
import com.example.rest_demo.model.SalesEmployee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalesEmployeeController {

    private final SalesEmployeeRepository salesEmployeeRepository;

    SalesEmployeeController(SalesEmployeeRepository salesEmployeeRepository) {
        this.salesEmployeeRepository = salesEmployeeRepository;
    }

    @GetMapping("/salesEmployees")
    List<SalesEmployee> all() {
        return salesEmployeeRepository.findAll();
    }
}
