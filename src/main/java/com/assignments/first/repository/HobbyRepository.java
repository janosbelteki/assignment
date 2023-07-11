package com.assignments.first.repository;

import com.assignments.first.repository.entities.HobbyEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface HobbyRepository extends JpaRepository<HobbyEntity, UUID> {
    Page<HobbyEntity> findByNameContainingAndLastDoneBetweenAndUserIdIn(
            String search,
            Timestamp startDate,
            Timestamp endDate,
            List<UUID> userIds,
            Pageable pageable
    );

    Page<HobbyEntity> findAll(Specification<HobbyEntity> specification, Pageable pageable);
}