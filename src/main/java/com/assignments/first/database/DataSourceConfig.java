package com.assignments.first.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

import static com.assignments.first.common.Constants.ADMIN_PASSWORD;
import static com.assignments.first.common.Constants.ADMIN_USER;
import static com.assignments.first.common.Constants.JDBC_URL;


@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(ADMIN_USER);
        dataSource.setPassword(ADMIN_PASSWORD);

        org.h2.tools.Server server = org.h2.tools.Server.createWebServer("-web", "-tcp");
        server.start();

        return dataSource;
    }
}
