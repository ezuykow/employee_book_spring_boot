package ru.ezuykow.employee_book.services.department_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ezuykow.employee_book.models.Employee;
import ru.ezuykow.employee_book.repositories.EmployeesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeesRepository employeesRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private  List<Employee> actualEmployees;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee(1,
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

    @Test // getExistingDepartments
    public void shouldReturnExistingDepartments() {
        final Set<Integer> actual = actualEmployees.stream()
                .map(Employee::getEmployeeDepartment)
                .collect(Collectors.toSet());
        final Set<Integer> expected = departmentService.getExistingDepartments();

        assertEquals(expected, actual);
    }

    @Test // getEmployeesFromDepartment
    public void shouldReturnEmployeesFromDepartment() {
        final int departmentId = 1;

        final List<Employee> actual = actualEmployees.stream()
                .filter(e -> e.getEmployeeDepartment() == departmentId)
                .collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeesFromDepartment(departmentId);

        assertEquals(expected, actual);
    }

    @Test // getSalarySumOfDepartment
    public void shouldReturnSalarySumOfDepartment() {
        final int departmentId = 1;

        final int actual = actualEmployees.stream()
                .filter(e -> e.getEmployeeDepartment() == departmentId)
                .mapToInt(Employee::getEmployeeSalary)
                .sum();
        final int expected = departmentService.getSalarySumOfDepartment(departmentId);

        assertEquals(expected, actual);
    }

    @Test // getEmployeesByDepartment
    public void shouldReturnEmployeesByDepartment() {
        final Map<Integer, List<Employee>> actual =
                actualEmployees.stream().map(Employee::getEmployeeDepartment).collect(Collectors.toSet()).stream()
                        .collect(Collectors.toMap(dept -> dept,
                                dept -> actualEmployees.stream().filter(e -> e.getEmployeeDepartment() == dept)
                                        .collect(Collectors.toList())));
        final Map<Integer, List<Employee>> expected = departmentService.getEmployeesByDepartment();

        assertEquals(expected, actual);
    }

    @Test // getMinSalaryOfDepartment
    public void shouldReturnMinSalaryOfDepartment() {
        final int department = 1;

        final int actual = actualEmployees.stream().filter(e -> e.getEmployeeDepartment() == department)
                .mapToInt(Employee::getEmployeeSalary).min().orElse(0);
        final int expected = departmentService.getMinSalaryOfDepartment(department);

        assertEquals(expected, actual);
    }

    @Test // getMaxSalaryOfDepartment
    public void shouldReturnMaxSalaryOfDepartment() {
        final int department = 1;

        final int actual = actualEmployees.stream().filter(e -> e.getEmployeeDepartment() == department)
                .mapToInt(Employee::getEmployeeSalary).max().orElse(0);
        final int expected = departmentService.getMaxSalaryOfDepartment(department);

        assertEquals(expected, actual);
    }

}