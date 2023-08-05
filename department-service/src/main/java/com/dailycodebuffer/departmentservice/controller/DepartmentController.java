package com.dailycodebuffer.departmentservice.controller;

import com.dailycodebuffer.departmentservice.model.Department;
import com.dailycodebuffer.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    //API de journalisation pour Java(logging)
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {} ", department);
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        return departmentRepository.findById(id);
    }
}
