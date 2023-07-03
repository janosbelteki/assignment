package com.assignments.first.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationRepository {

    List<String> findUsers();
}

@Repository
class DefaultApplicationRepository implements ApplicationRepository {
    @Override
    public List<String> findUsers() {
        // TODO: Implement the findUsers method
        List<String> returnValue = new ArrayList<>();
        return returnValue;
    }
}