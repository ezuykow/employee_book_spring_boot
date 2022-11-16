package ru.ezuykow.employee_book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ezuykow.employee_book.model.Employee;
import ru.ezuykow.employee_book.record.EmployeeRequest;
import ru.ezuykow.employee_book.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public Collection<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping()
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/salaries/sum")
    public int getSumOfSalaries() {
        return employeeService.getSumOfSalaries();
    }

    @GetMapping("/min-salary-employee")
    public Employee getEmployeeWithMinSalary() {
        return employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("/max-salary-employee")
    public Employee getEmployeeWithMaxSalary() {
        return employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/salary-more-average")
    public Collection<Employee> getEmployeesWithSalaryMoreAverage() {
        return employeeService.getEmployeesWithSalaryMoreAverage();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(IllegalArgumentException e) {
        return "Employee`s data must be valid";
    }
}
