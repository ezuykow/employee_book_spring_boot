package ru.ezuykow.employee_book.repositories;

import org.springframework.stereotype.Repository;
import ru.ezuykow.employee_book.models.Employee;

import java.util.Collection;
import java.util.List;

@Repository
public class EmployeesRepository {

    private static int lastId;
    private final List<Employee> employees;

    public EmployeesRepository(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        ++lastId;
    }

    public int getLastId() {
        return lastId;
    }
}
