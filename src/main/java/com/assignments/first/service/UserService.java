package com.assignments.first.service;

import com.assignments.first.common.FilterParams;
import com.assignments.first.common.PagingConfig;
import com.assignments.first.controller.UsersController;
import com.assignments.first.controller.dtos.requests.CreateUsersRequest;
import com.assignments.first.controller.dtos.requests.UserDataRestResponse;
import com.assignments.first.controller.dtos.responses.CreateUserResponse;
import com.assignments.first.controller.dtos.responses.HobbyResponse;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.exceptions.AssignmentExceptionHandler;
import com.assignments.first.repository.HobbyRepository;
import com.assignments.first.repository.UserRepository;
import com.assignments.first.repository.entities.HobbyEntity;
import com.assignments.first.repository.entities.UserEntity;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.assignments.first.common.Constants.ORDER_ASC;
import static com.assignments.first.common.Constants.QUEUE_CHECK;
import static com.assignments.first.repository.entities.UserEntity.createUserEntity;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;

    public UserService(UserRepository userRepository, HobbyRepository hobbyRepository) {
        this.userRepository = userRepository;
        this.hobbyRepository = hobbyRepository;
    }

    public void saveUsers(List<UserEntity> users) {
        userRepository.saveAll(users);
    }

    public UserResponse getUsers(List<String> userIds, PagingConfig pagingConfig) {
        List<UUID> uuidList = convertIds(userIds);
        Sort sort = getSort(pagingConfig);
        Pageable pageable = PageRequest.of(pagingConfig.getPageIndex(), pagingConfig.getPageLimit(), sort);

        if (CollectionUtils.isEmpty(uuidList)) {
            return new UserResponse(userRepository.findAll(pageable).getContent());
        } else {
            return new UserResponse(userRepository.findByIdIn(uuidList, pageable).getContent());
        }
    }

    public CreateUserResponse createUsers(CreateUsersRequest createUsersRequest) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserDataRestResponse currentUserDataRestResponse : createUsersRequest.getUserData()) {
            UserEntity userEntity = createUserEntity(currentUserDataRestResponse);
            userEntities.add(userEntity);
        }
        List<UserEntity> savedEntities = userRepository.saveAll(userEntities);

        List<String> userIds = new ArrayList<>();
        for (UserEntity userEntity : savedEntities) {
            UUID userId = userEntity.getId();
            userIds.add(userId.toString());
        }
        return new CreateUserResponse(userIds);
    }

    public HobbyResponse getHobbies(FilterParams filterParams, PagingConfig pagingConfig) {
        String search = filterParams.getSearch();
        Timestamp startDate = filterParams.getStartDate();
        Timestamp endDate = filterParams.getEndDate();
        List<String> userIds = filterParams.getUserIds();
        List<UUID> userUuidList = convertIds(userIds);

        Sort sort = getSort(pagingConfig);
        Pageable pageable = PageRequest.of(pagingConfig.getPageIndex(), pagingConfig.getPageLimit(), sort);

        Specification<HobbyEntity> specification = buildSpecification(
                search,
                startDate,
                endDate,
                userUuidList
        );

        Page<HobbyEntity> hobbies = hobbyRepository.findAll(specification, pageable);
        return new HobbyResponse(hobbies.getContent());
    }

    public HobbyResponse getUserHobbies(String userId) {
        UUID uuid = convertIds(List.of(userId)).get(0);
        return new HobbyResponse(hobbyRepository.findByUserId(uuid));
    }

    @Scheduled(fixedRate = 60000L)
    private void checkForExceptionsInQueue() {
        ResponseEntity<String> response = AssignmentExceptionHandler.handleAssignmentExceptions();
        logger.info("Check for exceptions in queue, response: " + response.getBody());
    }

    private Specification<HobbyEntity> buildSpecification(
            String search,
            Timestamp startDate,
            Timestamp endDate,
            List<UUID> userIds) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            if (search != null) {
                Expression<String> nameExpression = criteriaBuilder.upper(root.get("name"));
                String searchValue = "%" + search.toUpperCase() + "%";
                predicates.add(criteriaBuilder.like(nameExpression, searchValue));
            }
            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("lastDone"), startDate));
            }
            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("lastDone"), endDate));
            }
            if (!userIds.isEmpty()) {
                predicates.add(root.get("userId").in(userIds));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



    private List<UUID> convertIds(List<String> ids) {
        if (ids == null) {
            return null;
        }

        return ids.stream().map(UUID::fromString).collect(Collectors.toList());
    }

    private Sort getSort(PagingConfig pagingConfig) {
        return switch (pagingConfig.getOrder()) {
            case ORDER_ASC -> Sort.by(pagingConfig.getOrderBy()).ascending();
            default -> Sort.by(pagingConfig.getOrderBy()).descending();
        };
    }


}