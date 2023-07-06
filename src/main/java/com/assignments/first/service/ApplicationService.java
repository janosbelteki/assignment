package com.assignments.first.service;

import com.assignments.first.repository.ApplicationRepository;
import com.assignments.first.repository.entities.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

import static com.assignments.first.common.Constants.USER_DATA_PATH;

public interface ApplicationService {
    List<String> findUsers();
    void saveUsers(List<UserEntity> users);
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

    @Transactional
    public void saveUsers(List<UserEntity> users) {
        try {
            applicationRepository.saveAll(users);
            logger.info("User data saved successfully");
        } catch (Exception e) {
            logger.warn("DB error, rolling back...", e);
            e.printStackTrace();
        }
    }
}