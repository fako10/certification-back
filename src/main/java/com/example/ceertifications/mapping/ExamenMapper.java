package com.example.ceertifications.mapping;

import com.example.ceertifications.dto.ExamenDto;
import com.example.ceertifications.dto.QuestionDto;
import com.example.ceertifications.entities.Examen;
import com.example.ceertifications.entities.QuestionEntity;

public class ExamenMapper {

    public static Examen toEntity(ExamenDto dto) {
        Examen entity = new Examen();
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }

    public static ExamenDto toDto(Examen entity) {
        ExamenDto dto = new ExamenDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setLibelle(entity.getLibelle());
        dto.setCertification(entity.getCertificationEntity().getLibelle());
        return dto;
    }
}
