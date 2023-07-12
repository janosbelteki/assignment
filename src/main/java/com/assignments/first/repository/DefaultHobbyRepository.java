package com.assignments.first.repository;

import com.assignments.first.repository.entities.HobbyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public class DefaultHobbyRepository implements HobbyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void flush() {

    }

    @Override
    public <S extends HobbyEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<HobbyEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public HobbyEntity getOne(UUID uuid) {
        return null;
    }

    @Override
    public HobbyEntity getById(UUID uuid) {
        return null;
    }

    @Override
    public HobbyEntity getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends HobbyEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends HobbyEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends HobbyEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends HobbyEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<HobbyEntity> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<HobbyEntity> findAll() {
        return null;
    }

    @Override
    public List<HobbyEntity> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(HobbyEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends HobbyEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<HobbyEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<HobbyEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<HobbyEntity> findAll(Specification<HobbyEntity> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<HobbyEntity> findByUserId(UUID userId) {
        return null;
    }
}
