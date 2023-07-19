package com.assignments.first.common;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FilterParams {

    private String search;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<String> userIds;

    // If there are more than 8 parameters use builder instead

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

}
