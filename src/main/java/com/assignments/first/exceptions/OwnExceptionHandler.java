package com.assignments.first.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OwnExceptionHandler {
    @ExceptionHandler(AssignmentException.class)
    protected ResponseEntity<ErrorObject> handleOwnException(AssignmentException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorObject(e.getMessages().get(0)));
    }
}