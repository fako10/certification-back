package com.example.ceertifications.service;

import com.example.ceertifications.dto.ReponseQuestionDto;
import com.example.ceertifications.entities.ReponseQuestionsEntity;
import com.example.ceertifications.mapping.ReponseQuestionMapper;
import com.example.ceertifications.repositories.ReponseQuestionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReponseQuestionService {

    private ReponseQuestionsRepository reponseQuestionsRepository;

    public ReponseQuestionDto save(ReponseQuestionDto reponseQuestionDto) {
        ReponseQuestionsEntity reponseQuestionsEntity = ReponseQuestionMapper.toEntity(reponseQuestionDto);
        return ReponseQuestionMapper.toDto(reponseQuestionsRepository.save(reponseQuestionsEntity));
    }
}
