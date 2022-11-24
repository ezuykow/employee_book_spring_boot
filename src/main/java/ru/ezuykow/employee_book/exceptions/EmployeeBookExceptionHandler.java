package ru.ezuykow.employee_book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeBookExceptionHandler {

    @ExceptionHandler(IllegalNameException.class)
    public ResponseEntity<Object> illegalName(IllegalNameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> emptyBody(HttpMessageNotReadableException e) {
        return new ResponseEntity<>("Request body is missing!", HttpStatus.BAD_REQUEST);
    }
}
