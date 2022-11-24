package ru.ezuykow.employee_book.services.department_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ezuykow.employee_book.models.Employee;
import ru.ezuykow.employee_book.repositories.EmployeesRepository;
import ru.ezuykow.employee_book.services.DepartmentService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeesRepository employeesRepository;

    @Autowired
    public DepartmentServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Set<Integer> getExistingDepartments() {
        return employeesRepository.getEmployees().stream()
                .map(Employee::getEmployeeDepartment)
                .collect(Collectors.toSet());
    }

    @Override
    public List<Employee> getEmployeesFromDepartment(int departmentId) {
        return employeesRepository.getEmployees().stream()
                .filter(employee -> employee.getEmployeeDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public int getSalarySumOfDepartment(int departmentId) {
        return getEmployeesFromDepartment(departmentId).stream()
                .mapToInt(Employee::getEmployeeSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return getExistingDepartments().stream()
                .collect(Collectors.toMap(dept -> dept, this::getEmployeesFromDepartment));
    }

    @Override
    public int getMinSalaryOfDepartment(int departmentId) {
        return getEmployeesFromDepartment(departmentId).stream()
                .mapToInt(Employee::getEmployeeSalary)
                .min().orElseThrow();
    }

    @Override
    public int getMaxSalaryOfDepartment(int departmentId) {
        return getEmployeesFromDepartment(departmentId).stream()
                .mapToInt(Employee::getEmployeeSalary)
                .max().orElseThrow();
    }
}
