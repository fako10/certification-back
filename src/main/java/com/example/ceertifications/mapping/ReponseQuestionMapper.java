package com.example.ceertifications.mapping;


import com.example.ceertifications.dto.ReponseQuestionDto;
import com.example.ceertifications.entities.ReponseQuestionsEntity;

public class ReponseQuestionMapper {

    public static ReponseQuestionsEntity toEntity(ReponseQuestionDto dto) {
        ReponseQuestionsEntity entity = new ReponseQuestionsEntity();
        entity.setQuestionEntity(QuestionMapper.toEntity(dto.getQuestionDto()));
        return entity;
    }

    public static ReponseQuestionDto toDto(ReponseQuestionsEntity entity) {
        ReponseQuestionDto reponseQuestionDto = new ReponseQuestionDto();
        reponseQuestionDto.setQuestionDto(QuestionMapper.toDto(entity.getQuestionEntity()));
        return reponseQuestionDto;
    }
}
