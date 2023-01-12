package com.example.ceertifications.controller;

import com.example.ceertifications.dto.ReponseQuestionDto;
import com.example.ceertifications.entities.ReponseQuestionsEntity;
import com.example.ceertifications.exception.GlobalException;

import com.example.ceertifications.service.ExamenService;
import com.example.ceertifications.service.ReponseQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class QuestionController {

    ExamenService examenService;

    ReponseQuestionService reponseQuestionService;

    @GetMapping("/examens/create-user-examen-questions/{id}")
    public List<ReponseQuestionsEntity> getUserExamenQuestion(@PathVariable Long id) throws GlobalException {
        return  examenService.getUserExamenQuestion(id);
    }

    @PutMapping("/question/update-reponse-question")
    public ReponseQuestionDto updateReponseQuestion(ReponseQuestionDto reponseQuestionDto) {
        return reponseQuestionService.save(reponseQuestionDto);
    }

}
