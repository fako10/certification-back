package com.example.ceertifications.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserExamenDto {

    private long id;
    private long idUser;
    private long idExamen;
    private Date dateExamen;
    List<QuestionDto> questions;

}
    