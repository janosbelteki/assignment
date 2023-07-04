package com.assignments.first.repository.entities;

import com.assignments.first.common.enums.Gender;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    private int userId;
    String firstName;
    String lastName;
    int age;
    Enum<Gender> gender;
    List<Hobby> hobbies = null;

    public int getId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Enum<Gender> getGender() {
        return gender;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }
}

class Hobby {
    String name;
    int duration;
    Timestamp lastDone;
}
