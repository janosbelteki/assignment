package com.assignments.first.repository.entities;

import com.assignments.first.common.enums.Gender;
import com.assignments.first.controller.dtos.requests.UserDataRestResponse;
import com.assignments.first.exceptions.AssignmentException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.assignments.first.common.Constants.INVALID_GENDER_ERROR_LABEL;
import static com.assignments.first.common.Constants.USER_TABLE;

@Entity
@Table(name = USER_TABLE)
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "age")
    public int age;

    @Enumerated(EnumType.STRING)
    public Gender gender;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<HobbyEntity> hobbies;

    public UserEntity() {
    }

    public static UserEntity createUserEntity(UserDataRestResponse currentUserDataRestResponse) {
        Gender validGender = Arrays.stream(Gender.values())
                .filter(it -> it.getName().equals(currentUserDataRestResponse.gender))
                .findFirst()
                .orElseThrow(() -> new AssignmentException(INVALID_GENDER_ERROR_LABEL, "A"));
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(currentUserDataRestResponse.getFirstName());
        userEntity.setLastName(currentUserDataRestResponse.getLastName());
        userEntity.setAge(currentUserDataRestResponse.getAge());
        userEntity.setGender(validGender);
        userEntity.setHobbies(currentUserDataRestResponse.getHobbies());
        return userEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID userId) {
        this.id = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<HobbyEntity> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyEntity> hobbies) {
        this.hobbies = hobbies;
    }
}

