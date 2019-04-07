package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        updateDepartmentAndManagerWithDbData(employee);
        return repository.save(employee);
    }

    public Employee update(Long id, Employee newEmployeeData) {
        if (!repository.exists(id))
            throw new NoSuchElementException();

        newEmployeeData.setEmployeeNumber(id);

        updateDepartmentAndManagerWithDbData(newEmployeeData);

        Employee oldEmployeeData = repository.findOne(id);

        if (!Objects.equals(newEmployeeData.getDepartment(), oldEmployeeData.getDepartment())) {
            updateSubordinatesDepartments(newEmployeeData);
        }

        return repository.save(newEmployeeData);
    }

    private void updateDepartmentAndManagerWithDbData(Employee newEmployeeData) {
        Department department = newEmployeeData.getDepartment();
        if (department != null) {
            department = departmentService.show(department.getDepartmentNumber());
            newEmployeeData.setDepartment(department);
        }
        Employee manager = newEmployeeData.getManager();
        if (manager != null) {
            manager = show(manager.getEmployeeNumber());
            newEmployeeData.setManager(manager);
            newEmployeeData.setDepartment(manager.getDepartment());
        }
    }

    private void updateSubordinatesDepartments(Employee manager) {
        List<Employee> subordinates = repository.findEmployeesByManager_EmployeeNumber(manager.getEmployeeNumber());
        if (subordinates.size() > 0) {
            for (Employee subordinate : subordinates) {
                subordinate.setDepartment(manager.getDepartment());
                repository.save(subordinate);
                updateSubordinatesDepartments(subordinate);
            }
        }
    }

    public Boolean delete(Long id) {
        repository.delete(id);
        return true;
    }
}
