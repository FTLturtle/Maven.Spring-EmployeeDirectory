package io.zipcoder.persistenceapp.builders;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class EmployeeBuilder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long employeeNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String title;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date hireDate;

    private Long managerId;

    private Long departmentId;

    public EmployeeBuilder(String firstName, String lastName, String title, String phoneNumber, String email, Date hireDate, Long managerId, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hireDate = hireDate;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public EmployeeBuilder setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EmployeeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EmployeeBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public EmployeeBuilder setHireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public Long getManagerId() {
        return managerId;
    }

    public EmployeeBuilder setManagerId(Long managerId) {
        this.managerId = managerId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public EmployeeBuilder setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }
}
