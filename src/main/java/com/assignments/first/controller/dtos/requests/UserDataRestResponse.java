package com.assignments.first.controller.dtos.requests;

import com.assignments.first.common.enums.Gender;
import com.assignments.first.exceptions.AssignmentException;
import com.assignments.first.repository.entities.HobbyEntity;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static com.assignments.first.common.Constants.INVALID_GENDER_ERROR_LABEL;

public class UserDataRestResponse {
    private final String firstName;
    String lastName;
    int age;
    String gender;
    List<HobbyEntity> hobbies;

    public UserDataRestResponse(String firstName, String lastName, int age, String gender, List<HobbyEntity> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
    }
    // TODO: move to the entity itself
    public UserEntity toUserEntity() {
        Gender validGender = Arrays.stream(Gender.values())
                .filter(it -> it.getName().equals(gender))
                .findFirst()
                .orElseThrow(() -> new AssignmentException(INVALID_GENDER_ERROR_LABEL, HttpStatus.BAD_REQUEST));
        return new UserEntity(firstName, lastName, age, validGender, hobbies);
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public int getAge() { return age; }

    public String getGender() { return gender; }

    public List<HobbyEntity> getHobbies() {
        return hobbies;
    }
}
