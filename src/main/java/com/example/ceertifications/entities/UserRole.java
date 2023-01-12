package com.example.ceertifications.entities;

import java.util.stream.Stream;

public enum UserRole {
    USER,ADMIN;

    public static UserRole toEnum(String val) {
        return Stream.of(values())
                .filter(enume -> enume.name().equalsIgnoreCase(val))
                .findFirst()
                .orElse(UserRole.USER);
    }
}
