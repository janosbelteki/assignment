package com.assignments.first.controller.dtos.responses;

import com.assignments.first.repository.entities.HobbyEntity;

import java.util.List;

public class HobbyResponse {
    List<HobbyEntity> hobbies;

    public HobbyResponse(List<HobbyEntity> hobbies) {
        this.hobbies = hobbies;
    }

    public List<HobbyEntity> getHobbies() {
        return hobbies;
    }
}
