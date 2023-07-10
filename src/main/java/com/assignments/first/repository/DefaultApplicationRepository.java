package com.assignments.first.repository;

import com.assignments.first.repository.entities.Hobby;
import com.assignments.first.repository.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static com.assignments.first.common.Constants.HOBBY_TABLE;
import static com.assignments.first.common.Constants.USER_TABLE;

@Repository
public class DefaultApplicationRepository implements ApplicationRepository {
    //private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    /*public DefaultApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserEntity> getUsers() throws SQLException {
        String sql = "SELECT * FROM " + USER_TABLE;
        List<UserEntity> users = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(UserEntity.class)
        );
        return users;
    }*/


    /*@Override
    public void saveUsers(Connection connection, List<UserEntity> users) throws SQLException {
        String userInsertQuery = "INSERT INTO " + USER_TABLE + " (firstName, lastName, age, gender, hobbyId) VALUES (?, ?, ?, ?, ?)";
        String hobbyInsertQuery = "INSERT INTO " + HOBBY_TABLE + " (name, duration, lastDone) VALUES (?, ?, ?)";
        PreparedStatement userStatement = connection.prepareStatement(userInsertQuery, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement hobbyStatement = connection.prepareStatement(hobbyInsertQuery, Statement.RETURN_GENERATED_KEYS);

        connection.setAutoCommit(false);
        addUserAndHobbyEntitiesToStatement(users, hobbyStatement, userStatement);
        userStatement.executeBatch();
        connection.commit();
    }*/



    private void addHobbyEntitiesToStatement(UserEntity user, PreparedStatement hobbyStatement, PreparedStatement userStatement) throws SQLException {
        for (Hobby hobby : user.getHobbies()) {
            hobbyStatement.setString(1, hobby.getHobbyName());
            hobbyStatement.setInt(2, hobby.getHobbyDuration());
            hobbyStatement.setTimestamp(3, hobby.getHobbyLastDone());
            hobbyStatement.addBatch();
        }
        hobbyStatement.executeBatch();
        ResultSet hobbyKeys = hobbyStatement.getGeneratedKeys();
        int hobbyIndex = 1;

        while (hobbyKeys.next()) {
            userStatement.setObject(5, hobbyKeys.getObject(1));
            hobbyIndex++;
        }
    }

    private void addUserAndHobbyEntitiesToStatement(List<UserEntity> users, PreparedStatement hobbyStatement, PreparedStatement userStatement) throws SQLException {
        for (UserEntity user : users) {
            userStatement.setString(1, user.getFirstName());
            userStatement.setString(2, user.getLastName());
            userStatement.setInt(3, user.getAge());
            userStatement.setString(4, user.getGender().toString());

            if (user.getHobbies() != null && !user.getHobbies().isEmpty()) {
                addHobbyEntitiesToStatement(user, hobbyStatement, userStatement);
            } else {
                userStatement.setObject(5, null);
            }
            userStatement.addBatch();
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserEntity getOne(UUID uuid) {
        return null;
    }

    @Override
    public UserEntity getById(UUID uuid) {
        return null;
    }

    @Override
    public UserEntity getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public List<UserEntity> findAllById(Iterable<UUID> uuids) {
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
    public void delete(UserEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return null;
    }
}
