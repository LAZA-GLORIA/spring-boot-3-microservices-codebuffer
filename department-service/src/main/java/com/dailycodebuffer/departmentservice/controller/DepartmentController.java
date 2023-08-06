package com.dailycodebuffer.departmentservice.controller;

import com.dailycodebuffer.departmentservice.model.Department;
import com.dailycodebuffer.departmentservice.model.Employee;
import com.dailycodebuffer.departmentservice.repository.DepartmentRepository;
import com.dailycodebuffer.employeeservice.client.EmployeeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    //API de journalisation pour Java(logging)
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

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

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department with employees find");
        List<Department> departments
                = departmentRepository.findAll();
        departments.forEach(department -> {
            List<com.dailycodebuffer.employeeservice.model.Employee> employees =
                    employeeClient.findByIdDepartment(department.getId());

            List<com.dailycodebuffer.departmentservice.model.Employee> departmentEmployees =
                    employees.stream()
                            .map(this::convertToDepartmentEmployee)
                            .collect(Collectors.toList());

            department.setEmployees(departmentEmployees);
        });
        return departments;
    }

    public com.dailycodebuffer.departmentservice.model.Employee convertToDepartmentEmployee(
            com.dailycodebuffer.employeeservice.model.Employee employeeServiceEmployee) {

        // Création d'un nouvel objet Employee dans le paquet departmentservice.model
        // et copie des propriétés de l'objet Employee du paquet employeeservice.model
        return new com.dailycodebuffer.departmentservice.model.Employee(
                employeeServiceEmployee.id(),
                employeeServiceEmployee.departmentId(),
                employeeServiceEmployee.name(),
                employeeServiceEmployee.age(),
                employeeServiceEmployee.position());
    }

}
