package com.assignments.first.repository.entities;

import com.assignments.first.common.enums.Gender;

import java.util.List;

public class UserEntity {
    private String userId;
    String firstName;
    String lastName;
    Integer age;
    Enum<Gender> gender;
    List<String> hobbies = null;
}
