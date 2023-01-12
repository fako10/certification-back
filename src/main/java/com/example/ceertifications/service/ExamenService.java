package com.example.ceertifications.service;

import com.example.ceertifications.dto.QuestionDto;
import com.example.ceertifications.dto.ReponseDto;
import com.example.ceertifications.dto.UserExamenDto;
import com.example.ceertifications.entities.*;
import com.example.ceertifications.entities.enums.UserExamenStatut;
import com.example.ceertifications.exception.ErreurEnum;
import com.example.ceertifications.exception.GlobalException;
import com.example.ceertifications.mapping.QuestionMapper;
import com.example.ceertifications.mapping.ReponseMapper;
import com.example.ceertifications.mapping.UserExamenMapper;
import com.example.ceertifications.repositories.*;
import com.example.ceertifications.security.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamenService {

    private CertificationRepository certificationRepository;
    private ExamenRepository examenRepository;
    private UserExamenRepository userExamenRepository;
    private UserExamenQuestionRepository userExamenQuestionRepository;
    private UserExamenQuestionReponseRepository userExamenQuestionReponseRepository;
    UserDetailsServiceImpl userDetails;

    public List<Examen> getExamenByCertificationId(Long id){
        Optional<Certification> certification = certificationRepository.findById(id);
        List<Examen> examenList = certification.map(examenRepository::findExamenByCertificationEntity).orElse(new ArrayList<>());
        String chaine = "test";
        Class classe = chaine.getClass();
        System.out.println("classe de l'objet chaine = "+classe.getName());
        return examenList;
    }


    public UserExamenDto createUserExamen(Long idExamen) throws GlobalException {
        UserExamen userExamen = new UserExamen();
        Users user  = userDetails.getConnectedUser();
        userExamen.setUser(user);
        Examen examen = examenRepository.findById(idExamen).orElseThrow(() -> new GlobalException(ErreurEnum.RESOURCE_NOT_FOUND));
        userExamen.setExamen(examen);
        userExamen.setDateExamen(LocalDate.now());
        userExamen.setStatut(UserExamenStatut.En_Cours);
        userExamenRepository.save(userExamen);
        UserExamenDto userExamenDto = new UserExamenDto();
        userExamenDto.setId(userExamen.getId());
        userExamenDto.setIdExamen(examen.getId());
        userExamenDto.setIdUser(user.getId());
        userExamenDto.setQuestions(examen.getQuestionEntities().stream().map(QuestionMapper::toDto).collect(Collectors.toList()));
        return userExamenDto;
    }

    public List<ReponseQuestionsEntity> getUserExamenQuestion(Long idExamen) throws GlobalException {
        List<ReponseQuestionsEntity> reponseQuestionEntities = new ArrayList<>();
        return reponseQuestionEntities;
    }

    public UserExamenDto saveUserExamen(UserExamenDto userExamenDto) {
        //UserExamenDto savedUserExamen = new UserExamenDto();
        userExamenDto.getQuestions().forEach(x->saveQuestion(x, userExamenDto.getId()));

        return userExamenDto;
    }

    public void saveQuestion(QuestionDto questionDto, Long userExamenId) {
        UserExamenQuestionEntity userExamenQuestionEntity = new UserExamenQuestionEntity();
        UserExamen userExamen = userExamenRepository.findById(userExamenId).orElse(new UserExamen());
        userExamenQuestionEntity.setUserExamen(userExamen);
        userExamenQuestionEntity.setQuestionEntity(QuestionMapper.toEntity(questionDto));
        userExamenQuestionRepository.save(userExamenQuestionEntity);
        questionDto.getReponses().forEach(x->saveReponse(x, userExamenQuestionEntity));

    }
    public void saveReponse(ReponseDto reponseDto, UserExamenQuestionEntity userExamenQuestion) {
        UserExamenQuestionReponseEntity userExamenQuestionReponseEntity = new UserExamenQuestionReponseEntity();
        userExamenQuestionReponseEntity.setUserExamenQuestionEntity(userExamenQuestion);
        ReponseEntity reponseEntity = ReponseMapper.toEntity(reponseDto);
        userExamenQuestionReponseEntity.setReponseEntity(reponseEntity);
        userExamenQuestionReponseRepository.save(userExamenQuestionReponseEntity);
    }

    public UserExamenDto getUserExamen(Long id) {
        UserExamen userExamen = userExamenRepository.findById(id).orElse(new UserExamen());
        return UserExamenMapper.toDto(userExamen);
    }
}
