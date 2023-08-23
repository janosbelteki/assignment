package com.assignments.first.repository;

import com.assignments.first.repository.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Page<UserEntity> findByIdIn(List<UUID> userIds, Pageable pageable);
}


