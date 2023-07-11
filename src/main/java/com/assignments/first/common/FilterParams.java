package com.assignments.first.common;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class FilterParams {
    Optional<String> search;
    Optional<Timestamp> startDate;
    Optional<Timestamp> endDate;
    Optional<List<String>> userIds;

    public FilterParams(String search, Timestamp startDate, Timestamp endDate, List<String> userIds) {
        this.search = Optional.ofNullable(search);
        this.startDate = Optional.ofNullable(startDate);
        this.endDate = Optional.ofNullable(endDate);
        this.userIds = Optional.ofNullable(userIds);
    }

    public Optional<String> getSearch() {
        return search;
    }

    public Optional<Timestamp> getStartDate() {
        return startDate;
    }

    public Optional<Timestamp> getEndDate() {
        return endDate;
    }

    public Optional<List<String>> getUserIds() {
        return userIds;
    }
}
