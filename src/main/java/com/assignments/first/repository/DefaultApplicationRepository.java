package com.assignments.first.repository;

import com.assignments.first.repository.entities.Hobby;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.assignments.first.common.Constants.HOBBY_TABLE;
import static com.assignments.first.common.Constants.USER_TABLE;

@Repository
public class DefaultApplicationRepository implements ApplicationRepository {
    @Override
    public List<UserEntity> findUsers() {
        // TODO: Implement the findUsers method
        List<UserEntity> returnValue = new ArrayList<>();
        return returnValue;
    }

    @Override
    public void saveUsers(Connection connection, List<UserEntity> users) throws SQLException {
        String userInsertQuery = "INSERT INTO " + USER_TABLE + " (firstName, lastName, age, gender, hobbyId) VALUES (?, ?, ?, ?, ?)";
        String hobbyInsertQuery = "INSERT INTO " + HOBBY_TABLE + " (name, duration, lastDone) VALUES (?, ?, ?)";
        PreparedStatement userStatement = connection.prepareStatement(userInsertQuery, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement hobbyStatement = connection.prepareStatement(hobbyInsertQuery, Statement.RETURN_GENERATED_KEYS);

        connection.setAutoCommit(false);
        addUserAndHobbyEntitiesToStatement(users, hobbyStatement, userStatement);
        userStatement.executeBatch();
        connection.commit();
    }

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
}
