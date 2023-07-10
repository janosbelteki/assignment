package com.assignments.first.repository;

import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static com.assignments.first.common.Constants.USER_TABLE;


public interface ApplicationRepository extends JpaRepository<UserEntity, UUID> {
    //List<UserEntity> getUsers() throws SQLException;

    //void saveUsers(Connection connection, List<UserEntity> users) throws SQLException;
}


