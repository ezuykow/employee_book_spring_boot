package ru.ezuykow.employee_book.services.employee_service;

import org.springframework.stereotype.Service;
import ru.ezuykow.employee_book.models.Employee;
import ru.ezuykow.employee_book.dto.EmployeeRequest;
import ru.ezuykow.employee_book.repositories.EmployeesRepository;
import ru.ezuykow.employee_book.services.EmployeeService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.ezuykow.employee_book.services.employee_service.EmployeeServiceUtil.averageSalary;
import static ru.ezuykow.employee_book.services.employee_service.EmployeeServiceUtil.createEmployeeFromRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeesRepository employeesRepository;

    public EmployeeServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> getEmployees() {
        return employeesRepository.getEmployees();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        final Employee newEmployee =
                createEmployeeFromRequest(employeeRequest, employeesRepository.getLastId());
        employeesRepository.addEmployee(newEmployee);
        return newEmployee;
    }

    public int getSumOfSalaries() {
        return employeesRepository.getEmployees().stream()
                .mapToInt(Employee::getEmployeeSalary).sum();
    }

    public Employee getEmployeeWithMinSalary() {
        return employeesRepository.getEmployees().stream()
                .min(Comparator.comparingInt(Employee::getEmployeeSalary))
                .orElseGet(() -> null);
    }

    public Employee getEmployeeWithMaxSalary() {
        return employeesRepository.getEmployees().stream()
                .max(Comparator.comparingInt(Employee::getEmployeeSalary))
                .orElseGet(() -> null);
    }

    public List<Employee> getEmployeesWithSalaryMoreAverage() {
        final List<Employee> employees = employeesRepository.getEmployees();
        final int average = averageSalary(employees);
        return employees.stream()
                .filter(e -> e.getEmployeeSalary() > average)
                .collect(Collectors.toList());
    }
}
