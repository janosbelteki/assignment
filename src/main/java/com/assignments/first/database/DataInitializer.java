package com.assignments.first.database;

import com.assignments.first.repository.entities.UserEntity;
import com.assignments.first.service.ApplicationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.assignments.first.common.Constants.JDBC_URL;
import static com.assignments.first.common.Constants.USER_DATA_PATH;
import static com.assignments.first.common.Constants.USER_TABLE;

@Component
class DataInitializer {
    private final ApplicationService applicationService;
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    public DataInitializer(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostConstruct
    public void initializeData() throws Exception {
        Connection connection = DriverManager.getConnection(JDBC_URL);
        int rowCount = checkIfDbHasData(connection);
        if (rowCount == 0) {
            logger.info("Loading data from from: " + USER_DATA_PATH);
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(USER_DATA_PATH);
            List<UserEntity> users = objectMapper.readValue(inputStream, new TypeReference<>() {});

            applicationService.saveUsers(users);
        }
    }

    private int checkIfDbHasData(Connection connection) throws Exception {
        boolean tableExists = checkIfTableExistsInDb(connection);
        if (!tableExists) {
            createTable(connection);
        }
        String sql = "SELECT COUNT(*) FROM " + USER_TABLE;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int rowCount = 0;
        if (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    private boolean checkIfTableExistsInDb(Connection connection) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, USER_TABLE, null);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void createTable(Connection connection) throws Exception {
        String createTableQuery = "CREATE TABLE USER_TABLE (" +
                "userId INT PRIMARY KEY, " +
                "firstName VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age INT, " +
                "gender VARCHAR(255), " +
                "hobbies VARCHAR(255)" +
                ")";
        PreparedStatement statement = connection.prepareStatement(createTableQuery);
        statement.execute();
        statement.close();
    }
}
