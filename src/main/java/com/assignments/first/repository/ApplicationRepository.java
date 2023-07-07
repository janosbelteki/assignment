package com.assignments.first.repository;

import com.assignments.first.repository.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<UserEntity, Serializable> {
    List<UserEntity> findUsers();
}

@Repository
class DefaultApplicationRepository implements ApplicationRepository {
    @Override
    public List<UserEntity> findUsers() {
        // TODO: Implement the findUsers method
        List<UserEntity> returnValue = new ArrayList<>();
        return returnValue;
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Serializable serializable) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Serializable serializable) {
        return false;
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<UserEntity> findAllById(Iterable<Serializable> serializables) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Serializable serializable) {

    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Serializable> serializables) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}