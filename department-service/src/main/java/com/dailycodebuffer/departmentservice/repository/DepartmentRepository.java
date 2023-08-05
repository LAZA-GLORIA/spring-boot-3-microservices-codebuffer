package com.dailycodebuffer.departmentservice.repository;

import com.dailycodebuffer.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Dans cet exemple on utilise une classe personnalisée pour stocker les objets Department dans une liste de mémoire
 * Ce qui fait qu'on a pas besoin de base de données réelle.
 */
@Repository
public class DepartmentRepository {
    private List<Department> departments = new ArrayList<>();

    public Department addDepartment(Department department) {
        departments.add(department);
        return department;
    }

    public Department findById(Long id) {
        return departments.stream()
        .filter(department -> department.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Department> findAll() {
        return departments;
    }
}
