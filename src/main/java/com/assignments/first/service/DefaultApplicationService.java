package com.assignments.first.service;

import com.assignments.first.common.FilterParams;
import com.assignments.first.common.PagingConfig;
import com.assignments.first.controller.dtos.requests.CreateUsersRequest;
import com.assignments.first.controller.dtos.requests.UserData;
import com.assignments.first.controller.dtos.responses.CreateUserResponse;
import com.assignments.first.controller.dtos.responses.HobbyResponse;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.repository.HobbyRepository;
import com.assignments.first.repository.UserRepository;
import com.assignments.first.repository.entities.HobbyEntity;
import com.assignments.first.repository.entities.UserEntity;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.assignments.first.common.Constants.ORDER_ASC;
import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Service
class DefaultApplicationService implements ApplicationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Override
    public void saveUsers(List<UserEntity> users) {
        userRepository.saveAll(users);
    }

    @Override
    public UserResponse getUsers(List<String> userIds, PagingConfig pagingConfig) {
        List<UUID> uuidList = convertIds(userIds);
        Sort sort = getSort(pagingConfig);
        Pageable pageable = PageRequest.of(pagingConfig.getPageIndex(), pagingConfig.getPageLimit(), sort);

        if (CollectionUtils.isEmpty(uuidList)) {
            return new UserResponse(userRepository.findAll(pageable).getContent());
        } else {
            return new UserResponse(userRepository.findByUserIdIn(uuidList, pageable).getContent());
        }
    }

    @Override
    public CreateUserResponse createUsers(CreateUsersRequest createUsersRequest) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserData currentUserData : createUsersRequest.getUserData()) {
            UserEntity userEntity = currentUserData.toUserEntity();
            userEntities.add(userEntity);
        }
        List<UserEntity> savedEntities = userRepository.saveAll(userEntities);

        List<String> userIds = new ArrayList<>();
        for (UserEntity userEntity : savedEntities) {
            UUID userId = userEntity.getUserId();
            userIds.add(userId.toString());
        }
        return new CreateUserResponse(userIds);
    }

    /*@Override
    public HobbyResponse getHobbies(FilterParams filterParams, PagingConfig pagingConfig) {
        Optional<String> search = filterParams.getSearch();
        Optional<Timestamp> startDate = filterParams.getStartDate();
        Optional<Timestamp> endDate = filterParams.getEndDate();
        Optional<List<String>> userIds = filterParams.getUserIds();
        List<UUID> userUuidList = convertIds(userIds.orElse(Collections.emptyList()));

        Sort sort = getSort(pagingConfig);
        Pageable pageable = PageRequest.of(pagingConfig.getPageIndex(), pagingConfig.getPageLimit(), sort);

        return new HobbyResponse(
                hobbyRepository.findByNameContainingAndLastDoneBetweenAndUserIdIn(
                        search,
                        startDate,
                        endDate,
                        userUuidList,
                        pageable
                ).getContent()
        );
    }*/

    public HobbyResponse getHobbies(FilterParams filterParams, PagingConfig pagingConfig) {
        Optional<String> search = filterParams.getSearch();
        Optional<Timestamp> startDate = filterParams.getStartDate();
        Optional<Timestamp> endDate = filterParams.getEndDate();
        Optional<List<String>> userIds = filterParams.getUserIds();
        List<UUID> userUuidList = convertIds(userIds.orElse(Collections.emptyList()));

        Sort sort = getSort(pagingConfig);
        Pageable pageable = PageRequest.of(pagingConfig.getPageIndex(), pagingConfig.getPageLimit(), sort);

        Specification<HobbyEntity> specification = buildSpecification(
                search.orElse(null),
                startDate.orElse(null),
                endDate.orElse(null),
                userUuidList
        );

        Page<HobbyEntity> hobbies = hobbyRepository.findAll(specification, pageable);
        return new HobbyResponse(hobbies.getContent());
    }

    private Specification<HobbyEntity> buildSpecification(
            String search,
            Timestamp startDate,
            Timestamp endDate,
            List<UUID> userIds
    ) {
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

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public UserEntity getById(Long aLong) {
        return null;
    }

    @Override
    public UserEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public List<UserEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return null;
    }

    private List<UUID> convertIds(List<String> ids) {
        if (ids != null) {
            return ids.stream().map(UUID::fromString).collect(Collectors.toList());
        } else {
            return null;
        }

    }

    private Sort getSort(PagingConfig pagingConfig) {
        return switch (pagingConfig.getOrder()) {
            case ORDER_ASC -> Sort.by(pagingConfig.getOrderBy()).ascending();
            default -> Sort.by(pagingConfig.getOrderBy()).descending();
        };
    }

    private List<Boolean> getFilterParamNullabilities(
            Optional<String> search,
            Optional<Timestamp> startDate,
            Optional<Timestamp> endDate,
            List<UUID> userUuidList
    ) {
        List<Boolean> filterParamNullabilities = listOf();
        List filterParams = listOf(search, startDate, endDate, userUuidList);
        for (Object filterParam : filterParams) {
            filterParamNullabilities.add(CollectionUtils.isEmpty((Collection<?>) filterParam));
        }
        return filterParamNullabilities;
    }
}