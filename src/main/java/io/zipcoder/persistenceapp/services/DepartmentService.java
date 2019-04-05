package io.zipcoder.persistenceapp.services;


import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Department> index() {
        return repository.findAll();
    }

    public Department show(Long id) {
        return repository.findOne(id);
    }

    public Department create(Department department) {
        return repository.save(department);
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
