package com.assignments.first.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.LinkedList;
import java.util.Queue;

@ControllerAdvice
public class AssignmentExceptionHandler {
    private static Queue<AssignmentException> exceptionQueue = new LinkedList<>();

    public static void enqueueException(AssignmentException e) {
        exceptionQueue.offer(e);
    }

    public static ResponseEntity<String> handleAssignmentExceptions() {
        while (!exceptionQueue.isEmpty()) {
            AssignmentException e = exceptionQueue.poll();

            e.printStackTrace();
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
        return ResponseEntity.ok("No exceptions to handle");
    }
}