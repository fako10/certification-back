package com.example.ceertifications.dto;



import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private long id;
    private String intitule;
    private String description;
    private String typeReponse;
    private Long examen;
    List<ReponseDto> reponses;
    private boolean isCorrecte;

}
