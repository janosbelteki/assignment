package com.assignments.first.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

import static com.assignments.first.common.Constants.HOBBY_TABLE;

@Entity
@Table(name = HOBBY_TABLE)
public class HobbyEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "name")
    public String name;

    @Column(name = "duration")
    public int duration;

    @Column(name = "last_done")
    public Timestamp lastDone;

    @Column(name = "userId")
    private UUID userId;

    public HobbyEntity() {
    }

    public HobbyEntity(String name, int duration, String lastDone) {
        this.name = name;
        this.duration = duration;
        this.lastDone = Timestamp.valueOf(lastDone);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getLastDone() { return lastDone; }
    public void setLastDone(Timestamp lastDone) {
        this.lastDone = lastDone;
    }
}
