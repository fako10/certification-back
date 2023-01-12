package com.example.ceertifications.mapping;

import com.example.ceertifications.dto.ReponseDto;
import com.example.ceertifications.entities.ReponseEntity;

public class ReponseMapper {

    public static ReponseEntity toEntity(ReponseDto dto) {
        ReponseEntity entity = new ReponseEntity();
        entity.setId(dto.getId());
        entity.setIntitule(dto.getIntitule());
        entity.setCorrecte(dto.isCorrecte());
        return entity;
    }

    public static ReponseDto toDto(ReponseEntity entity) {
        ReponseDto dto = new ReponseDto();
        dto.setId(entity.getId());
        dto.setCorrecte(entity.isCorrecte());
        dto.setIntitule(entity.getIntitule());
        dto.setQuestion(entity.getQuestionEntity().getId());
        dto.setSelectionne(false);
        return dto;
    }
}
