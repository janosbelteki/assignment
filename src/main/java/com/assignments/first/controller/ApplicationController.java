package com.assignments.first.controller;

import com.assignments.first.common.PagingConfig;
import com.assignments.first.controller.dtos.requests.UserCreationRequest;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.exceptions.AssignmentExceptionHandler;
import com.assignments.first.repository.entities.UserEntity;
import com.assignments.first.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import static com.assignments.first.common.Constants.API_PATH;
import static com.assignments.first.common.Constants.API_USER;
import static com.assignments.first.common.Constants.DEFAULT_PAGE_INDEX;
import static com.assignments.first.common.Constants.DEFAULT_PAGE_LIMIT;
import static com.assignments.first.common.Constants.ORDER_ASC;
import static com.assignments.first.common.Constants.USER_LIST_DEFAULT_ORDER_BY;
import static com.assignments.first.common.PagingConfig.getCorrectIndex;
import static com.assignments.first.common.PagingConfig.getCorrectLimit;
import static com.assignments.first.common.PagingConfig.getCorrectOrder;
import static com.assignments.first.common.PagingConfig.getCorrectOrderBy;

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
    public ResponseEntity<UserResponse> getUsers(
            @RequestParam List<String> userIds,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int pageLimit,
            @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) int pageIndex,
            @RequestParam(defaultValue = ORDER_ASC) String order,
            @RequestParam(defaultValue = USER_LIST_DEFAULT_ORDER_BY) String orderBy
    ) throws SQLException {
        PagingConfig pagingConfig = new PagingConfig(
                getCorrectLimit(pageLimit),
                getCorrectIndex(pageIndex),
                getCorrectOrder(order),
                getCorrectOrderBy(orderBy)
        );
        logger.info("Getting users, userIds: " + userIds + ", pagingConfig: " + pagingConfig);
        UserResponse userResponse = applicationService.getUsers(userIds, pagingConfig);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUsers(
            @RequestBody UserCreationRequest userCreationRequest
    ) {
        logger.info("Creating new user(s), userCreationRequest: " + userCreationRequest);

        //List<String> userIds = userService.userRegistration(languageCode, userReg, countryCode, paymentPlatformType);
        return ResponseEntity.ok().body(null);//.body(userId);
    }
}

