package io.zipcoder.persistenceapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long departmentNumber;

    @NotNull
    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee departmentManager;

    public Department() {
    }

    public Department(String departmentName, Employee departmentManager) {
        this.departmentName = departmentName;
        this.departmentManager = departmentManager;
    }

    public Long getDepartmentNumber() {
        return departmentNumber;
    }

    public Department setDepartmentNumber(Long departmentNumber) {
        this.departmentNumber = departmentNumber;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public Employee getDepartmentManager() {
        return departmentManager;
    }

    public Department setDepartmentManager(Employee departmentManager) {
        this.departmentManager = departmentManager;
        return this;
    }
}
