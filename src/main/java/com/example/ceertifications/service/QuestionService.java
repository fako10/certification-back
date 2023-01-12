package com.example.ceertifications.service;


import com.example.ceertifications.dto.ExamenDto;
import com.example.ceertifications.dto.ReponseQuestionDto;
import com.example.ceertifications.entities.Examen;
import com.example.ceertifications.repositories.ExamenRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private ExamenRepository examenRepository;
    public List<ReponseQuestionDto> getQuestionReponseByExamen(Long id){
        Examen examenEntity = examenRepository.findById(id).orElse(new Examen());
        ExamenDto examenDto = new ExamenDto();
        examenDto.setExamen(examenEntity.getId());
        //examenDto.setExamen(examenEntity.getQuestionEntities().stream().map(x-> x.getReponseEntity()).map(x->ReponseQuestionMapper.toDto(x)).collect(Collectors.toList()));
        return examenDto.getReponseQuestionDtos();
    }

}
