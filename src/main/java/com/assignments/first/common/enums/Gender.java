package com.assignments.first.common.enums;

public enum Gender {
    MALE("male"),
    FEMALE("female");
    public final String name;

    private Gender(String name) {
        this.name = name;
    }
}
