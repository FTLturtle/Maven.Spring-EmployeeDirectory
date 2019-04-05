package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.builders.EmployeeBuilder;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Iterable<Employee> index() {
        return repository.findAll();
    }

    public Employee show(Long id) {
        return repository.findOne(id);
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee create(EmployeeBuilder employeeBuilder) {
        Employee employee = new Employee();

    }

    public Employee update(Long id, Employee newEmployeeData) {
        newEmployeeData.setEmployeeNumber(repository.findOne(id).getEmployeeNumber());
        return repository.save(newEmployeeData);
    }

    public Boolean delete(Long id) {
        repository.delete(id);
        return true;
    }
}
