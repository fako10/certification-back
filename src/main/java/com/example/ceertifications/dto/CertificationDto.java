package com.example.ceertifications.dto;

import lombok.Data;

@Data
public class CertificationDto {

    private long id;
    private String libelle;
    private String description;
    private Float amount;
}
