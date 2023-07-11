package com.assignments.first.common;

public class Constants {
    // Endpoint path
    public static final String API_PATH = "/api";
    public static final String API_USER = "/users";

    // Endpoint paging
    public static final String DEFAULT_PAGE_LIMIT = "10";
    public static final String DEFAULT_PAGE_INDEX = "0";
    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";
    public static final String USER_LIST_DEFAULT_ORDER_BY = "lastName";
    public static final String HOBBY_LIST_DEFAULT_ORDER_BY = "name";

    // DB
    public static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    public static final String ADMIN_USER = "admin";
    public static final String ADMIN_PASSWORD = "admin123";
    public static final String USER_TABLE = "users";
    public static final String HOBBY_TABLE = "hobbies";

    // Resource
    public static final String USER_DATA_PATH = "user_data/user_data.json";

    // Error labels
    public static final String INVALID_PAGE_LIMIT_ERROR_LABEL = "invalid_page_limit_error_label";
    public static final String INVALID_PAGE_INDEX_ERROR_LABEL = "invalid_page_index_error_label";
    public static final String INVALID_GENDER_ERROR_LABEL = "invalid_gender_error_label";
}
