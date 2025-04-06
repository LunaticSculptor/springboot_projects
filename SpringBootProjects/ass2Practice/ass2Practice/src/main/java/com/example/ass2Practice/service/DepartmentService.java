package com.example.ass2Practice.service;

import com.example.ass2Practice.exception.IdNotFoundException;
import com.example.ass2Practice.model.Department;
import com.example.ass2Practice.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) throws IdNotFoundException {
        return departmentRepository.findById(id).orElseThrow(()->new IdNotFoundException("Id not Found"));
    }
    @Transactional
    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }
    @Transactional
    public Department updateDepartment(Long id, Department department) throws IdNotFoundException {
        Department existingDep = departmentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("No such id exists"));
        existingDep.setName(department.getName());
        return existingDep; // No need to explicitly call save()
    }

    @Transactional
    public void deleteDepartment(Long id) throws IdNotFoundException {
        if(!departmentRepository.existsById(id)){
            throw new IdNotFoundException("No such id exists");
        }departmentRepository.deleteById(id);
    }

}
