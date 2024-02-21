package com.example.ceertifications.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserExamenDto {

    private long id;
    private long idUser;
    private long idExamen;
    private String libelleCertification;
    private String libelleExamen;
    private Integer duree;
    private LocalDateTime dateExamen;
    List<QuestionDto> questions;
    private Integer nbrQuestions;
    private Integer nbrQuestionsCorrectes;
    private Boolean resultat;
    private long pourcentage;

}
    