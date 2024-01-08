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
    private LocalDateTime dateExamen;
    List<QuestionDto> questions;

}
    