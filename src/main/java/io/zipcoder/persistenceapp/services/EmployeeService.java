package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmployeeRepository repository;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository repository, DepartmentService departmentService) {
        this.repository = repository;
        this.departmentService = departmentService;
    }

    public Iterable<Employee> index() {
        return repository.findAll();
    }

    public Employee show(Long id) {
        return repository.findOne(id);
    }

    public Employee create(Employee employee) {
        Employee manager = employee.getManager();
        if (manager != null) {
            manager = show(manager.getEmployeeNumber());
            employee.setManager(manager);
        }
        Department department = employee.getDepartment();
        if (department != null) {
            department = departmentService.show(department.getDepartmentNumber());
            employee.setDepartment(department);
        }
        return repository.save(employee);
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
