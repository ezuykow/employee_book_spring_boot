package ru.ezuykow.employee_book.services.employee_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ezuykow.employee_book.dto.EmployeeRequest;
import ru.ezuykow.employee_book.exceptions.IllegalNameException;
import ru.ezuykow.employee_book.models.Employee;
import ru.ezuykow.employee_book.repositories.EmployeesRepository;
import ru.ezuykow.employee_book.services.EmployeeService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeesRepository  employeesRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private List<Employee> actualEmployees;
    private Employee employee1;

    @BeforeEach
    public void setUp() {
        employee1 = new Employee(1,
                "Ivan", "Ivanov",
                30_000, 1);
        Employee employee2 = new Employee(2,
                "Petr", "Petrov",
                31_000, 2);
        Employee employee3 = new Employee(3,
                "Katya", "Pavlova",
                50_000, 3);

        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3));

        when(employeesRepository.getEmployees()).thenReturn(actualEmployees);
    }

    @Test //getEmployees
    public void shouldReturnListOfEmployeesWhenCallGetEmployees() {
        List<Employee> expected = employeeService.getEmployees();
        assertEquals(expected, actualEmployees);
    }

    @Test //addEmployee
    public void shouldThrowIllegalNameExceptionWhenIllegalName() {
        EmployeeRequest badEmployee = new EmployeeRequest();
        badEmployee.setFirstName("123");
        badEmployee.setLastName("123");
        badEmployee.setDepartment(1);
        badEmployee.setSalary(10_000);

        when(employeesRepository.getLastId()).thenReturn(1);
        assertThrows(IllegalNameException.class, () -> employeeService.addEmployee(badEmployee));
    }

    @Test //addEmployee
    public void shouldReturnNewEmployeeWhenAddEmployee() {
        final Employee actual = employee1;

        EmployeeRequest employee = new EmployeeRequest();
        employee.setFirstName(actual.getEmployeeFirstName());
        employee.setLastName(actual.getEmployeeLastName());
        employee.setDepartment(actual.getEmployeeDepartment());
        employee.setSalary(actual.getEmployeeSalary());

        when(employeesRepository.getLastId()).thenReturn(0);
        Employee expected = employeeService.addEmployee(employee);
        assertEquals(expected, actual);
    }

    @Test // getSumOfSalaries
    public void shouldReturnRightSum() {
        final int actual = actualEmployees.stream()
                .mapToInt(Employee::getEmployeeSalary)
                .sum();
        final int expected = employeeService.getSumOfSalaries();

        assertEquals(expected, actual);
    }

    @Test // getEmployeeWithMinSalary
    public void shouldReturnRightEmployeeWithMinSalary() {
        final Employee actual = actualEmployees.stream()
                .min(Comparator.comparingInt(Employee::getEmployeeSalary)).get();
        final Employee expected = employeeService.getEmployeeWithMinSalary();

        assertEquals(expected, actual);
    }

    @Test // getEmployeeWithMaxSalaryWithMaxSalary
    public void shouldReturnRightEmployeeWithMaxSalary() {
        final Employee actual = actualEmployees.stream()
                .max(Comparator.comparingInt(Employee::getEmployeeSalary)).get();
        final Employee expected = employeeService.getEmployeeWithMaxSalary();

        assertEquals(expected, actual);
    }

    @Test // getEmployeesWithSalaryMoreAverage
    public void shouldReturnEmployeesWithSalaryMoreAverage() {
        final int average = actualEmployees.stream()
                .mapToInt(Employee::getEmployeeSalary)
                .sum()
                / actualEmployees.size();

        final List<Employee> expected = actualEmployees.stream()
                .filter(e -> e.getEmployeeSalary() > average)
                .collect(Collectors.toList());
        final List<Employee> actual = employeeService.getEmployeesWithSalaryMoreAverage();

        assertEquals(expected, actual);
    }
}