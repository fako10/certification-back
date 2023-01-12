package com.example.ceertifications.dto;

import lombok.Data;

@Data
public class ReponseDto {

    private long id;
    private String intitule;
    private boolean correcte;
    private Long question;
    private boolean selectionne;

}
