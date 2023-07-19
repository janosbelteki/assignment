package com.assignments.first.controller.dtos.responses;

import com.assignments.first.repository.entities.UserEntity;

import java.util.List;

public class UserResponse {
    private final List<UserEntity> users;

    public UserResponse(List<UserEntity> users) {
        this.users = users;
    }

    public List<UserEntity> getUsers() {
        return users;
    }
}
