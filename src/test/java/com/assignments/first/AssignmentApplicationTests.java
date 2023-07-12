package com.assignments.first;

import com.assignments.first.common.PagingConfig;
import com.assignments.first.common.enums.Gender;
import com.assignments.first.controller.ApplicationController;
import com.assignments.first.controller.dtos.responses.UserResponse;
import com.assignments.first.database.DataInitializer;
import com.assignments.first.repository.DefaultHobbyRepository;
import com.assignments.first.repository.DefaultUserRepository;
import com.assignments.first.repository.HobbyRepository;
import com.assignments.first.repository.UserRepository;
import com.assignments.first.repository.entities.HobbyEntity;
import com.assignments.first.repository.entities.UserEntity;
import com.assignments.first.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest
class AssignmentApplicationTests {

    @Mock
    private ApplicationService applicationService;
    private ApplicationController applicationController;
    private UserRepository userRepository;
    private HobbyRepository hobbyRepository;


    private List<UserEntity> testUsers;
    private List<HobbyEntity> testHobbies;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        applicationController = new ApplicationController(applicationService);
        userRepository = new DefaultUserRepository();
        hobbyRepository = new DefaultHobbyRepository();


        HobbyEntity hobby1 = new HobbyEntity("hobby1", 1, "2000-01-01T00:00:00.000+00:00");
        HobbyEntity hobby2 = new HobbyEntity("hobby2", 2, "2000-02-01T00:00:00.000+00:00");
        HobbyEntity hobby3 = new HobbyEntity("hobby3", 3, "2000-03-01T00:00:00.000+00:00");

        List<HobbyEntity> hobbies3 = new ArrayList<>();
        List<HobbyEntity> hobbies1 = new ArrayList<>(Arrays.asList(hobby1, hobby2, hobby3));
        List<HobbyEntity> hobbies2 = new ArrayList<>(List.of(hobby1));

        UserEntity user1 = new UserEntity("Adam", "Aldrin", 10, Gender.MALE, hobbies1);
        UserEntity user2 = new UserEntity("Beatrice", "Baker", 20, Gender.FEMALE, hobbies2);
        UserEntity user3 = new UserEntity("Courtney", "Cooker", 30, Gender.FEMALE, hobbies3);
        testUsers = new ArrayList<>(Arrays.asList(user1, user2, user3));
        testHobbies = new ArrayList<>(Arrays.asList(hobby1, hobby2, hobby3));

        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.initializeData();

        testUsers = userRepository.saveAll(testUsers);
        testHobbies = hobbyRepository.saveAll(testHobbies);
    }

    @Test
    void testGetUsers() throws SQLException {
        List<String> userIds = testUsers.stream().map( u -> u.getId().toString() ).toList();
        int pageLimit = 10;
        int pageIndex = 0;
        String order = "asc";
        String orderBy = "name";
        PagingConfig pagingConfig = new PagingConfig(pageLimit, pageIndex, order, orderBy);
        UserResponse expectedResponse = new UserResponse(testUsers);

        UserResponse response = applicationService.getUsers(userIds, pagingConfig);

        //when(applicationService.getUsers(userIds, pagingConfig)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, response);

//        // Perform the test
//        ResponseEntity<UserResponse> response = applicationController.getUsers(userIds, pageLimit, pageIndex, order, orderBy);
//
//        // Verify the response
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expectedResponse, response.getBody());
//
//        // Verify that the service method was called with the correct arguments
//        verify(applicationService).getUsers(userIds, pagingConfig);
    }

    /*@Test
    void contextLoads() {
    }*/
}
