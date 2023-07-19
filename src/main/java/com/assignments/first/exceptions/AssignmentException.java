package com.assignments.first.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AssignmentException extends RuntimeException {
    private final String message;

    public AssignmentException(String id, String repositorySource) {
        this.message = String.format("Resource with '%s', was not found in repository: '%s'.", id, repositorySource);
    }

    public String getMessages() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}