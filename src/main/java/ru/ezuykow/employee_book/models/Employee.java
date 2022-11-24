package ru.ezuykow.employee_book.models;

import java.util.Objects;

public class Employee {

    private final int employeeId;
    private final String employeeFirstName;
    private final String employeeLastName;
    private final int employeeSalary;
    private final int employeeDepartment;

    public Employee(int employeeId, String employeeFirstName, String employeeLastName,
                    int employeeSalary, int employeeDepartment) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeSalary = employeeSalary;
        this.employeeDepartment = employeeDepartment;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public int getEmployeeDepartment() {
        return employeeDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (employeeSalary != employee.employeeSalary) return false;
        if (employeeDepartment != employee.employeeDepartment) return false;
        if (!Objects.equals(employeeFirstName, employee.employeeFirstName))
            return false;
        return Objects.equals(employeeLastName, employee.employeeLastName);
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (employeeFirstName != null ? employeeFirstName.hashCode() : 0);
        result = 31 * result + (employeeLastName != null ? employeeLastName.hashCode() : 0);
        result = 31 * result + employeeSalary;
        result = 31 * result + employeeDepartment;
        return result;
    }
}
