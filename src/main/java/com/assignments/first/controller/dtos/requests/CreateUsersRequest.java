package com.assignments.first.controller.dtos.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class CreateUsersRequest {
    List<UserDataRestResponse> userDatumRestResponses;

    @JsonCreator
    public CreateUsersRequest(List<UserDataRestResponse> userDatumRestResponses) { this.userDatumRestResponses = userDatumRestResponses; }

    public List<UserDataRestResponse> getUserData() { return userDatumRestResponses; }
}

