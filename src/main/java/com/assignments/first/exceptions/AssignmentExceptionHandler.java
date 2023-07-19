package com.assignments.first.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AssignmentExceptionHandler {
    @ExceptionHandler(AssignmentException.class)
    protected ResponseEntity<String> handleAssignmentException(AssignmentException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}