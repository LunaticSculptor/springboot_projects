package com.example.ass2Practice.controller;

import com.example.ass2Practice.exception.IdNotFoundException;
import com.example.ass2Practice.model.Department;
import com.example.ass2Practice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Department>> getDepartmentById(@PathVariable Long id) throws IdNotFoundException {
//        return ResponseEntity.ok(departmentService.getDepartmentById(id));
        Department department = departmentService.getDepartmentById(id);
        EntityModel<Department> resource = EntityModel.of(department);
        Link selfLink = linkTo(methodOn(DepartmentController.class).getDepartmentById(id)).withSelfRel();
        Link updateLink = linkTo(methodOn(DepartmentController.class).updateDepartment(id, null)).withRel("update").withType("PUT");
        Link deleteLink = linkTo(methodOn(DepartmentController.class).deleteDepartment(id)).withRel("delete").withType("DELETE");
        resource.add(selfLink, updateLink, deleteLink);
        return ResponseEntity.ok(resource);
    }
    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) throws IdNotFoundException {
        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) throws IdNotFoundException {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
