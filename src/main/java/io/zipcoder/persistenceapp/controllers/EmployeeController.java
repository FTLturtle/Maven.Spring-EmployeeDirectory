package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.builders.EmployeeBuilder;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> index() {
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> show(@PathVariable Long id) {
        return new ResponseEntity<>(service.show(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(service.create(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        if (service.show(id) != null) {
            return new ResponseEntity<>(service.update(id, employee), HttpStatus.OK);
        } else {
            employee.setEmployeeNumber(id);
            return create(employee);
        }
    }

    @PutMapping("/employees/{id}/manager")
    public ResponseEntity<Employee> updateManager(@PathVariable Long id, @RequestBody Long managerId) {
        Employee updatedEmployee = service.show(id);
        updatedEmployee.setManager(service.show(managerId));
        return new ResponseEntity<>(service.update(id, updatedEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Boolean> destroy(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }
}
