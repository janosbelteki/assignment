package com.assignments.first;

import com.assignments.first.common.enums.Gender;
import com.assignments.first.repository.DefaultApplicationRepository;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AssignmentApplication {

    @Autowired
    private DefaultApplicationRepository applicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }
}
