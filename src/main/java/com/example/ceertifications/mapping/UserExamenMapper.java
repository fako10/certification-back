package com.example.ceertifications.mapping;

import com.example.ceertifications.dto.QuestionDto;
import com.example.ceertifications.dto.ReponseDto;
import com.example.ceertifications.dto.UserExamenDto;
import com.example.ceertifications.entities.ReponseEntity;
import com.example.ceertifications.entities.UserExamen;
import com.example.ceertifications.entities.UserExamenQuestionEntity;
import com.example.ceertifications.entities.UserExamenQuestionReponseEntity;
import com.example.ceertifications.repositories.UserExamenQuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

public final class UserExamenMapper {

    private UserExamenMapper() {
    }

    public static UserExamenDto toDto(UserExamen entity) {
        UserExamenDto dto = new UserExamenDto();
        dto.setId(entity.getId());
        dto.setIdExamen(dto.getIdExamen());
        dto.setIdUser(entity.getUser().getId());
        dto.setQuestions(entity.getQuestions().stream().map(x->mapUserExamenQuestionToQuestionDto(x)).collect(Collectors.toList()));
        return dto;
    }

    private static QuestionDto mapUserExamenQuestionToQuestionDto(UserExamenQuestionEntity userExamenQuestionEntity) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setExamen(userExamenQuestionEntity.getUserExamen().getExamen().getId());
        questionDto.setIntitule(userExamenQuestionEntity.getQuestionEntity().getIntitule());
        questionDto.setDescription(userExamenQuestionEntity.getQuestionEntity().getDescription());
        questionDto.setTypeReponse(userExamenQuestionEntity.getQuestionEntity().getTypeReponse());
        questionDto.setReponses(userExamenQuestionEntity.getQuestions().stream().map(x->mapReponseDto(x)).collect(Collectors.toList()));
        return QuestionMapper.toDto(userExamenQuestionEntity.getQuestionEntity());
    }

    private static ReponseDto mapReponseDto(UserExamenQuestionReponseEntity userExamenQuestionReponseEntity) {
        ReponseDto reponseDto = ReponseMapper.toDto(userExamenQuestionReponseEntity.getReponseEntity());
        reponseDto.setSelectionne(userExamenQuestionReponseEntity.isSelected());
        return reponseDto;
    }
}
