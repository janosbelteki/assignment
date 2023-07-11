package com.assignments.first.controller.dtos.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class CreateUsersRequest {
    List<UserData> userData;

    @JsonCreator
    public CreateUsersRequest(List<UserData> userData) { this.userData = userData; }

    public List<UserData> getUserData() { return userData; }
}

