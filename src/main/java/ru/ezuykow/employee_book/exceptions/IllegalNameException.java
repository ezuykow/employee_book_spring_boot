package ru.ezuykow.employee_book.exceptions;

public class IllegalNameException extends RuntimeException{

    public IllegalNameException() {
        super("Employee`s data must be valid");
    }
}
