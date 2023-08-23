package com.assignments.first.database;

import com.assignments.first.service.UserService;
import com.assignments.first.repository.entities.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static com.assignments.first.common.Constants.ADMIN_PASSWORD;
import static com.assignments.first.common.Constants.ADMIN_USER;
import static com.assignments.first.common.Constants.HOBBY_TABLE;
import static com.assignments.first.common.Constants.JDBC_URL;
import static com.assignments.first.common.Constants.USER_DATA_PATH;
import static com.assignments.first.common.Constants.USER_TABLE;

@Component
public class DataInitializer {
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    public DataInitializer() {

    }

    @PostConstruct
    public void initializeData() throws Exception {
        Connection connection = DriverManager.getConnection(JDBC_URL, ADMIN_USER, ADMIN_PASSWORD);
        connection.setAutoCommit(true);
        int rowCount = checkIfDbHasData(connection);
        if (rowCount == 0) {
            logger.info("Loading data from from: " + USER_DATA_PATH);
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(USER_DATA_PATH);
            List<UserEntity> users = objectMapper.readValue(inputStream, new TypeReference<>() {});

            userService.saveUsers(users);
        }
    }

    int checkIfDbHasData(Connection connection) throws Exception {
        createHobbyTable(connection);
        createUserTable(connection);

        String sql = "SELECT COUNT(*) FROM " + USER_TABLE;
        Statement statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(sql);
        int rowCount = 0;
        if (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    private static void createUserTable(Connection connection) throws Exception {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " (" +
                "id UUID DEFAULT RANDOM_UUID() PRIMARY KEY, " +
                "firstName VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age INT, " +
                "gender VARCHAR(255), " +
                "FOREIGN KEY (id) REFERENCES " + HOBBY_TABLE + "(id)" +
                ")";
        PreparedStatement statement = connection.prepareStatement(createTableQuery);
        statement.execute();
        statement.close();
    }

    private static void createHobbyTable(Connection connection) throws Exception {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + HOBBY_TABLE + " (" +
                "id UUID DEFAULT RANDOM_UUID() PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "duration INT, " +
                "lastDone TIMESTAMP, " +
                "FOREIGN KEY (id) REFERENCES " + USER_TABLE + "(id)" +
                ")";
        PreparedStatement statement = connection.prepareStatement(createTableQuery);
        statement.execute();
        statement.close();
    }
}
