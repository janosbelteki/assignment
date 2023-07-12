package com.assignments.first.controller;

import com.assignments.first.common.FilterParams;
import com.assignments.first.common.PagingConfig;
import com.assignments.first.controller.dtos.requests.CreateUsersRequest;
import com.assignments.first.controller.dtos.responses.CreateUserResponse;
import com.assignments.first.controller.dtos.responses.HobbyResponse;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.exceptions.AssignmentExceptionHandler;
import com.assignments.first.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.assignments.first.common.Constants.API_PATH;
import static com.assignments.first.common.Constants.API_USER;
import static com.assignments.first.common.Constants.DEFAULT_PAGE_INDEX;
import static com.assignments.first.common.Constants.DEFAULT_PAGE_LIMIT;
import static com.assignments.first.common.Constants.HOBBY_LIST_DEFAULT_ORDER_BY;
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
            @RequestParam(required=false) List<String> userIds,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int pageLimit,
            @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) int pageIndex,
            @RequestParam(defaultValue = ORDER_ASC) String order,
            @RequestParam(defaultValue = USER_LIST_DEFAULT_ORDER_BY) String orderBy
    ) throws SQLException {
        PagingConfig pagingConfig = new PagingConfig(
                getCorrectLimit(pageLimit),
                getCorrectIndex(pageIndex),
                getCorrectOrder(order),
                getCorrectOrderBy(orderBy, USER_LIST_DEFAULT_ORDER_BY)
        );
        logger.info("Getting users, userIds: " + userIds + ", pagingConfig: " + pagingConfig);
        UserResponse userResponse = applicationService.getUsers(userIds, pagingConfig);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponse> createUsers(
            @RequestBody CreateUsersRequest createUserRequest
    ) {
        logger.info("Create new user(s), createUserRequest: " + createUserRequest);
        CreateUserResponse createUserResponse = applicationService.createUsers(createUserRequest);
        return ResponseEntity.ok().body(createUserResponse);
    }

    @GetMapping(value = "/hobbies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HobbyResponse> getHobbies(
            @RequestParam(required=false) String search,
            @RequestParam(required=false) Timestamp startDate,
            @RequestParam(required=false) Timestamp endDate,
            @RequestParam(required=false) List<String> userIds,
            @RequestParam(defaultValue = DEFAULT_PAGE_LIMIT) int pageLimit,
            @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) int pageIndex,
            @RequestParam(defaultValue = ORDER_ASC) String order,
            @RequestParam(defaultValue = HOBBY_LIST_DEFAULT_ORDER_BY) String orderBy
    ) {
        PagingConfig pagingConfig = new PagingConfig(
                getCorrectLimit(pageLimit),
                getCorrectIndex(pageIndex),
                getCorrectOrder(order),
                getCorrectOrderBy(orderBy, HOBBY_LIST_DEFAULT_ORDER_BY)
        );
        FilterParams filterParams = new FilterParams(search, startDate, endDate, userIds);
        logger.info("Get hobbies, filterParams: " + filterParams + ", pagingConfig: " + pagingConfig);
        HobbyResponse hobbyResponse = applicationService.getHobbies(filterParams, pagingConfig);
        return ResponseEntity.ok().body(hobbyResponse);
    }

    @GetMapping(value = "/{userId}/hobbies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HobbyResponse> getUserHobbies(
            @PathVariable("userId") String userId
    ) {
        logger.info("Get hobbies for user, userId: " + userId);
        HobbyResponse hobbyResponse = applicationService.getUserHobbies(userId);
        return ResponseEntity.ok().body(hobbyResponse);
    }
}

