package io.zipcoder.persistenceapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Department {
    @Id
    @GeneratedValue
    Long departmentNumber;

    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee departmentManager;

    public Department() {
    }

    public Department(String departmentNumberString) {
        this.departmentNumber = Long.parseLong(departmentNumberString);
    }

    public Department(String departmentName, Employee departmentManager) {
        this.departmentName = departmentName;
        this.departmentManager = departmentManager;
    }

    public Long getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Long departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(Employee departmentManager) {
        this.departmentManager = departmentManager;
    }
}
