package com.assignments.first.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AssignmentException extends RuntimeException {
    private final List<String> messages;
    private final HttpStatus httpStatus;

    public AssignmentException(List<String> messages, HttpStatus httpStatus) {
        this.messages = messages;
        this.httpStatus = httpStatus;
    }

    public AssignmentException(String label, HttpStatus httpStatus) {
        this.messages = new ArrayList<>();
        this.messages.add(label);
        this.httpStatus = httpStatus;
    }

    public List<String> getMessages() {
        return messages;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}