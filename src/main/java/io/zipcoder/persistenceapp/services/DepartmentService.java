package io.zipcoder.persistenceapp.services;


import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private DepartmentRepository repository;
    private EmployeeService employeeService;

    @Autowired
    public DepartmentService(DepartmentRepository repository, @Lazy EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    public Iterable<Department> index() {
        return repository.findAll();
    }

    public Department show(Long id) {
        return repository.findOne(id);
    }

    public Department create(Department department) {
        Employee manager = department.getDepartmentManager();
        if (manager != null) {
            manager = employeeService.show(manager.getEmployeeNumber());
            department.setDepartmentManager(manager);
        }

        Department savedDepartment = repository.save(department);

        if (manager != null) {
            manager.setDepartment(savedDepartment);
            manager.setManager(null);
            employeeService.update(manager.getEmployeeNumber(), manager);
        }

        return savedDepartment;
    }

    public Department update(Long id, Department newDepartmentData) {
        newDepartmentData.setDepartmentNumber(repository.findOne(id).getDepartmentNumber());
        return repository.save(newDepartmentData);
    }

    public Boolean delete(Long id) {
        repository.delete(id);
        return true;
    }
}
