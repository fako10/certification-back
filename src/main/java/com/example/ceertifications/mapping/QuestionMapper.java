package com.example.ceertifications.mapping;

import com.example.ceertifications.dto.QuestionDto;
import com.example.ceertifications.entities.QuestionEntity;


import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionEntity toEntity(QuestionDto dto) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setDescription(dto.getDescription());
        questionEntity.setId(dto.getId());
        questionEntity.setIntitule(dto.getIntitule());
        questionEntity.setTypeReponse(dto.getTypeReponse());
        return questionEntity;
    }

    public static QuestionDto toDto(QuestionEntity entity) {
        QuestionDto dto = new QuestionDto();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setExamen(entity.getExamen().getId());
        dto.setTypeReponse(entity.getTypeReponse());
        dto.setReponses(entity.getReponseEntities().stream().map(ReponseMapper::toDto).collect(Collectors.toList()));
        return dto;
    }
}
