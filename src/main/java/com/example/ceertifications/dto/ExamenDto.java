package com.example.ceertifications.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamenDto {

    Long examen;
    List<ReponseQuestionDto> reponseQuestionDtos;
    Long utilisateur;

}
