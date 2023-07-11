package com.assignments.first.controller.dtos.responses;

import java.util.List;

public class CreateUserResponse {
    List<String> userIds;

    public CreateUserResponse(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }
}
