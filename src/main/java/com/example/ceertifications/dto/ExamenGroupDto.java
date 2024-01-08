package com.example.ceertifications.dto;

import com.example.ceertifications.entities.Examen;
import com.example.ceertifications.entities.UserExamen;
import lombok.Data;

import java.util.List;

@Data
public class ExamenGroupDto {

    private ExamenDto examen;
    private List<UserExamenDto> groupExamens;
}


