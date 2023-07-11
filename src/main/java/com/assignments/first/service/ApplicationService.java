package com.assignments.first.service;

import com.assignments.first.common.PagingConfig;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationService extends JpaRepository<UserEntity, Long> {
    UserResponse getUsers(List<String> userIds, PagingConfig pagingConfig) throws SQLException;
    void saveUsers(List<UserEntity> users);
}

