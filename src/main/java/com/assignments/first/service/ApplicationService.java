package com.assignments.first.service;

import com.assignments.first.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationService {

    List<String> findUsers();
}

@Service
class DefaultApplicationService implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    public DefaultApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<String> findUsers() {
        // TODO: Implement the userRegistration method

        return applicationRepository.findUsers();
    }
}