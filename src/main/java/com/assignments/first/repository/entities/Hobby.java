package com.assignments.first.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

import static com.assignments.first.common.Constants.HOBBY_TABLE;

@Entity
@Table(name = HOBBY_TABLE)
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hobbyId;

    @Column(name = "name")
    public String name;

    @Column(name = "duration")
    public int duration;

    @Column(name = "last_done")
    public Timestamp lastDone;

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return name;
    }

    public void setHobbyName(String name) {
        this.name = name;
    }
}
