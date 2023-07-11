package com.assignments.first.controller.dtos.requests;

import com.assignments.first.repository.entities.Hobby;

import java.util.List;

public class UserCreationRequest {
    private List<UserData> userData;

    public UserCreationRequest(List<UserData> userData) {
        this.userData = userData;
    }
}

class UserData {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private List<Hobby> hobbies;

    public UserData(String firstName, String lastName, int age, String gender, List<Hobby> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
    }
}