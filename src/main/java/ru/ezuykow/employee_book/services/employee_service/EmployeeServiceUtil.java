package ru.ezuykow.employee_book.services.employee_service;

import org.apache.commons.lang3.StringUtils;
import ru.ezuykow.employee_book.dto.EmployeeRequest;
import ru.ezuykow.employee_book.exceptions.IllegalNameException;
import ru.ezuykow.employee_book.models.Employee;

import java.util.List;

public class EmployeeServiceUtil {

    static Employee createEmployeeFromRequest(EmployeeRequest employeeRequest, int lastId) {
        validateEmployeeData(employeeRequest);
        return new Employee(++lastId,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getSalary(),
                employeeRequest.getDepartment());
    }

    static void validateEmployeeData(EmployeeRequest employeeRequest) {
        final String firstName = employeeRequest.getFirstName();
        final String lastName = employeeRequest.getLastName();
        checkName(firstName, lastName);
        employeeRequest.setFirstName(StringUtils.capitalize(firstName));
        employeeRequest.setLastName(StringUtils.capitalize(lastName));
    }

    static void checkName(String firstName, String lastName) {
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)
                || !firstName.chars().allMatch(Character::isLetter)
                || !lastName.chars().allMatch(Character::isLetter)) {
            throw new IllegalNameException();
        }
    }

    static int averageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToInt(Employee::getEmployeeSalary)
                .sum()
                / employees.size();
    }
}
