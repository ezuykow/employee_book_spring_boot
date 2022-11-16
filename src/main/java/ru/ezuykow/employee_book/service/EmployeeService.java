package ru.ezuykow.employee_book.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.ezuykow.employee_book.model.Employee;
import ru.ezuykow.employee_book.record.EmployeeRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static int COUNT;
    private final List<Employee> employees;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        final Employee newEmployee = createEmployeeFromRequest(employeeRequest);
        employees.add(newEmployee);
        return newEmployee;
    }

    public int getSumOfSalaries() {
        return employees.stream().mapToInt(Employee::getEmployeeSalary).sum();
    }

    public Employee getEmployeeWithMinSalary() {
        return employees.stream()
                .min(Comparator.comparingInt(Employee::getEmployeeSalary))
                .orElseGet(() -> null);
    }

    public Employee getEmployeeWithMaxSalary() {
        return employees.stream()
                .max(Comparator.comparingInt(Employee::getEmployeeSalary))
                .orElseGet(() -> null);
    }

    public List<Employee> getEmployeesWithSalaryMoreAverage() {
        final int average = averageSalary();
        return employees.stream()
                .filter(e -> e.getEmployeeSalary() > average)
                .collect(Collectors.toList());
    }

    private Employee createEmployeeFromRequest(EmployeeRequest employeeRequest) {
        if (StringUtils.isEmpty(employeeRequest.getFirstName())
                || StringUtils.isEmpty(employeeRequest.getLastName())) {
            throw new IllegalArgumentException();
        }
        return new Employee(++COUNT,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getSalary(),
                employeeRequest.getDepartment());
    }

    private int averageSalary() {
        return employees.stream()
                .mapToInt(Employee::getEmployeeSalary)
                .sum()
                / employees.size();
    }
}
