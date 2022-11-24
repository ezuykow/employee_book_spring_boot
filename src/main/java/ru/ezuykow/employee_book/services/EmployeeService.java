package ru.ezuykow.employee_book.services;

import ru.ezuykow.employee_book.models.Employee;
import ru.ezuykow.employee_book.dto.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();
    Employee addEmployee(EmployeeRequest employeeRequest);
    Employee getEmployeeWithMinSalary();
    Employee getEmployeeWithMaxSalary();
    List<Employee> getEmployeesWithSalaryMoreAverage();
    int getSumOfSalaries();
}
