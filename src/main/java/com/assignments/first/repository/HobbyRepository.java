package com.assignments.first.repository;

import com.assignments.first.repository.entities.HobbyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HobbyRepository extends JpaRepository<HobbyEntity, UUID> {
    Page<HobbyEntity> findAll(Specification<HobbyEntity> specification, Pageable pageable);
    List<HobbyEntity> findByUserId(UUID userId);
}