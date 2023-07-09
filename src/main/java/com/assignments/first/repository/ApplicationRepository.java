package com.assignments.first.repository;

import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface ApplicationRepository extends JpaRepository<UserEntity, Serializable> {
    List<UserEntity> findUsers();
}
