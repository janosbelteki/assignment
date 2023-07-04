package com.assignments.first;

import com.assignments.first.repository.entities.UserEntity;
import com.google.gson.Gson;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static com.assignments.first.common.Constants.JDBC_URL;
import static com.assignments.first.common.Constants.USER_DATA_PATH;

public class JsonLoaderToDb {

    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            UserEntity[] data = gson.fromJson(new FileReader(USER_DATA_PATH), UserEntity[].class);
            Connection connection = DriverManager.getConnection(JDBC_URL);
            createTable(connection);
            insertData(connection, data);

            System.out.println("User data loaded successfully");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws Exception {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY, " +
                "firstName VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age INT, " +
                "gender VARCHAR(255), " +
                "hobbies VARCHAR(255)" +
                ")";
        PreparedStatement statement = connection.prepareStatement(createTableSql);
        statement.executeUpdate();
        statement.close();
    }

    private static void insertData(Connection connection, UserEntity[] data) throws Exception {
        String insertSql = "INSERT INTO users (id, firstName, lastName, age, gender, hobbies) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertSql);

        for (UserEntity user : data) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getGender().toString());
            statement.setString(6, user.getHobbies());

            statement.executeUpdate();
        }

        statement.close();
    }
}