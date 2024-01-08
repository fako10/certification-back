package com.example.ceertifications.mapping;

import com.example.ceertifications.dto.QuestionDto;
import com.example.ceertifications.dto.ReponseDto;
import com.example.ceertifications.dto.UserExamenDto;
import com.example.ceertifications.entities.QuestionEntity;
import com.example.ceertifications.entities.ReponseEntity;
import com.example.ceertifications.entities.UserExamen;
import com.example.ceertifications.entities.UserExamenQuestionEntity;
import com.example.ceertifications.entities.UserExamenQuestionReponseEntity;
import com.example.ceertifications.repositories.UserExamenQuestionRepository;

import java.util.ArrayList;
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
        dto.setDateExamen(entity.getDateExamen());
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(UserExamenQuestionEntity userExamenQuestionEntity : entity.getQuestions()) {
            QuestionDto questionDto = UserExamenMapper.mapUserExamenQuestionToQuestionDto(userExamenQuestionEntity);
            questionDto.setIsCorrecte(userExamenQuestionEntity.isReponseCorrecte());
            questionDtos.add(questionDto);
        }
        dto.setQuestions(questionDtos);
        //dto.setQuestions(entity.getQuestions().stream().map(x->mapUserExamenQuestionToQuestionDto(x)).collect(Collectors.toList()));
        return dto;
    }

    private static QuestionDto mapUserExamenQuestionToQuestionDto(UserExamenQuestionEntity userExamenQuestionEntity) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setExamen(userExamenQuestionEntity.getUserExamen().getExamen().getId());
        questionDto.setIntitule(userExamenQuestionEntity.getQuestionEntity().getIntitule());
        questionDto.setDescription(userExamenQuestionEntity.getQuestionEntity().getDescription());
        questionDto.setTypeReponse(userExamenQuestionEntity.getQuestionEntity().getTypeReponse());
        //questionDto.setReponses(userExamenQuestionEntity.getQuestions().stream().map(x->mapReponseDto(x)).collect(Collectors.toList()));
        List<ReponseDto> reponseDtos = new ArrayList<>();
        for(UserExamenQuestionReponseEntity userQuestion : userExamenQuestionEntity.getQuestions()) {
          ReponseDto reponseDto = mapReponseDto(userQuestion);
          reponseDtos.add(reponseDto);
        }
        questionDto.setReponses(reponseDtos);

        //return QuestionMapper.toDto(userExamenQuestionEntity.getQuestionEntity());
        return questionDto;
    }

    private static ReponseDto mapReponseDto(UserExamenQuestionReponseEntity userExamenQuestionReponseEntity) {
        ReponseDto reponseDto = ReponseMapper.toDto(userExamenQuestionReponseEntity.getReponseEntity());
        reponseDto.setSelectionne(userExamenQuestionReponseEntity.isSelected());
        return reponseDto;
    }
}
