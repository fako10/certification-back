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
            questionDto.setCorrecte(userExamenQuestionEntity.isReponseCorrecte());
            questionDtos.add(questionDto);
        }
        dto.setQuestions(questionDtos);
        dto.setNbrQuestions(entity.getNbrQuestions());
        dto.setNbrQuestionsCorrectes(entity.getNbrQuestionsCorrectes());
        dto.setResultat(entity.getResultat());
        dto.setLibelleExamen(entity.getExamen().getLibelle());
        dto.setLibelleCertification(entity.getExamen().getCertificationEntity().getLibelle());
        dto.setDuree(entity.getExamen().getDuree());
        //dto.setQuestions(entity.getQuestions().stream().map(x->mapUserExamenQuestionToQuestionDto(x)).collect(Collectors.toList()));
        if(dto.getNbrQuestions() != null && dto.getNbrQuestionsCorrectes() != null) {
            long pourcentage = (dto.getNbrQuestionsCorrectes() * 100) / dto.getNbrQuestions();
            dto.setPourcentage(pourcentage);
        }
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
