package com.assignments.first;

import com.assignments.first.common.enums.Gender;
import com.assignments.first.repository.ApplicationRepository;
import com.assignments.first.repository.DefaultApplicationRepository;
import com.assignments.first.repository.entities.Hobby;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AssignmentApplication {

    @Autowired
    private DefaultApplicationRepository applicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    @PostConstruct
    private void initDb() {
        UserEntity user = new UserEntity();
        user.setFirstName("Peter");
        user.setLastName("Schwarz");
        user.setAge(18);
        user.setGender(Gender.MALE);
        //user.setHobbies(new ArrayList<Hobby>());
        applicationRepository.save(user);
    }

}
