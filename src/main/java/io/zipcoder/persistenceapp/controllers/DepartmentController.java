package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/API")
public class DepartmentController {
    private DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/departments")
    public ResponseEntity<Iterable<Department>> index() {
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> show(@PathVariable Long id) {
        return new ResponseEntity<>(service.show(id), HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> create(@RequestBody Department department) {
        return new ResponseEntity<>(service.create(department), HttpStatus.CREATED);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department department) {
        return new ResponseEntity<>(service.update(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Boolean> destroy(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }
}
