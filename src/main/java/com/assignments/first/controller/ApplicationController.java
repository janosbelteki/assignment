package com.assignments.first.controller;

import com.assignments.first.exceptions.AssignmentExceptionHandler;
import com.assignments.first.repository.entities.UserEntity;
import com.assignments.first.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.assignments.first.common.Constants.API_PATH;
import static com.assignments.first.common.Constants.API_USER;

@RestController
@Validated
@RequestMapping(API_PATH + API_USER)
public class ApplicationController extends AssignmentExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> getUsers(
    ) {
        logger.info("Find users types");
        List<UserEntity> users = applicationService.findUsers();
        return ResponseEntity.ok().body(users);
    }


    /*
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userRegistration(
            @RequestHeader(required = false, defaultValue = DEFAULT_LANG) String languageCode,
            @RequestHeader(required = false, defaultValue = DEFAULT_COUNTRY) String countryCode,
            @RequestHeader(required = false, defaultValue = DEFAULT_PLATFORM_TYPE) String platformType,
            @Valid @RequestBody UserRegistrationDto userReg) {
        logger.info("Registration user info, languageCode: " + languageCode + ", userRegistration: " +
                userReg + ", countryCode: " + countryCode + ", paymentPlatformType: " + paymentPlatformType);

        String userId = userService.userRegistration(languageCode, userReg, countryCode, paymentPlatformType);
        return ResponseEntity.ok().body(userId);
    }*/
}

