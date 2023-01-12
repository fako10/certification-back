package com.example.ceertifications.entities;

import java.util.stream.Stream;

public enum Statut {
    EN_COURS, TERMINE;

    public static Statut toEnum(String val) {
        return Stream.of(values())
                .filter(enume -> enume.name().equalsIgnoreCase(val))
                .findFirst()
                .orElse(Statut.EN_COURS);
    }
}
