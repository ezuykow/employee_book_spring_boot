package ru.ezuykow.employee_book.model;

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
}
