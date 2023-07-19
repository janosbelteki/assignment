package com.assignments.first.repository;

import com.assignments.first.common.FilterParams;
import com.assignments.first.common.PagingConfig;
import com.assignments.first.controller.dtos.requests.CreateUsersRequest;
import com.assignments.first.controller.dtos.responses.CreateUserResponse;
import com.assignments.first.controller.dtos.responses.HobbyResponse;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Page<UserEntity> findByIdIn(List<UUID> userIds, Pageable pageable);
    void saveUsers(List<UserEntity> users);
    UserResponse getUsers(List<String> userIds, PagingConfig pagingConfig) throws SQLException;
    CreateUserResponse createUsers(CreateUsersRequest createUsersRequest);
    HobbyResponse getHobbies(FilterParams filterParams, PagingConfig pagingConfig);
    HobbyResponse getUserHobbies(String userId);

}


