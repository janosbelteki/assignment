package com.assignments.first.repository;

import com.assignments.first.repository.entities.UserEntity;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface ApplicationRepository {
    List<UserEntity> findUsers();
    void saveUsers(Connection connection, List<UserEntity> users) throws SQLException;
}


