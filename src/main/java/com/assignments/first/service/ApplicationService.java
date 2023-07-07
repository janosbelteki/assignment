package com.assignments.first.service;

import com.assignments.first.repository.ApplicationRepository;
import com.assignments.first.repository.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface ApplicationService {
    List<UserEntity> findUsers();
    void saveUsers(List<UserEntity> users);
}

@Service
class DefaultApplicationService implements ApplicationService {
    //private final ApplicationRepository applicationRepository;
    private final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        userEntity = applicationRepository.save(userEntity);
        return userEntity;
    }


    @Override
    public List<UserEntity> findUsers() {
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