package io.zipcoder.persistenceapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Department {
    @Id
    @GeneratedValue
    Long departmentNumber;

    @NotNull
    private String departmentName;

    @OneToOne
    @JsonBackReference
    private Employee departmentManager;

    public Department() {
    }

    public Department(String departmentNumberString) {
        this(Long.parseLong(departmentNumberString));
    }

    public Department(Long id) {
        this.departmentNumber = id;
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
