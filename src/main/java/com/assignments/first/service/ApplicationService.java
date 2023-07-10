package com.assignments.first.service;

import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationService extends JpaRepository<UserEntity, Long> {
    //List<UserEntity> getUsers() throws SQLException;
    void saveUsers(List<UserEntity> users);
}

