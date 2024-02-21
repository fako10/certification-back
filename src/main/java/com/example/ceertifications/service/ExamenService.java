package com.example.ceertifications.service;

import com.example.ceertifications.dto.ExamenDto;
import com.example.ceertifications.dto.ExamenGroupDto;
import com.example.ceertifications.dto.QuestionDto;
import com.example.ceertifications.dto.ReponseDto;
import com.example.ceertifications.dto.UserExamenDto;
import com.example.ceertifications.entities.*;
import com.example.ceertifications.entities.enums.UserExamenStatut;
import com.example.ceertifications.exception.ErreurEnum;
import com.example.ceertifications.exception.GlobalException;
import com.example.ceertifications.mapping.ExamenMapper;
import com.example.ceertifications.mapping.QuestionMapper;
import com.example.ceertifications.mapping.ReponseMapper;
import com.example.ceertifications.mapping.UserExamenMapper;
import com.example.ceertifications.repositories.*;
import com.example.ceertifications.security.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<ExamenDto> getExamenByCertificationId(Long certificationId){
        Optional<Certification> certification = certificationRepository.findById(certificationId);
        List<Examen> examenList = certification.map(examenRepository::findExamenByCertificationEntity).orElse(new ArrayList<>());
        String chaine = "test";
        Class classe = chaine.getClass();
        System.out.println("classe de l'objet chaine = "+classe.getName());
        return examenList.stream().map(x-> ExamenMapper.toDto(x)).collect(Collectors.toList());
    }

    public ExamenDto getExamenById(Long examenId) {
        Examen examen = examenRepository.findById(examenId).orElse(new Examen());
        return ExamenMapper.toDto(examen);
    }

    public List<ExamenGroupDto> getExamenGroupByCertificationId(Long id){
        Certification certification = certificationRepository.findById(id).orElse(new Certification());
        List<Examen> examenList = examenRepository.findExamenByCertificationEntity(certification);
        List<ExamenGroupDto> examenGroups = new ArrayList<>();
        Users user  = userDetails.getConnectedUser();

        for(Examen entity: examenList) {
            ExamenGroupDto examenGroup = new ExamenGroupDto();
            List<UserExamen> userExamenList = userExamenRepository.findByUserAndExamen(user, entity);
            examenGroup.setExamen(ExamenMapper.toDto(entity));
            examenGroup.setGroupExamens(userExamenList.stream().map(x->UserExamenMapper.toDto(x))
                    .collect(Collectors.toList())
            );
            examenGroups.add(examenGroup);
        }
        return examenGroups;
    }


    public UserExamenDto createUserExamen(Long idExamen) throws GlobalException {
        UserExamen userExamen = new UserExamen();
        Users user  = userDetails.getConnectedUser();
        userExamen.setUser(user);
        Examen examen = examenRepository.findById(idExamen).orElseThrow(() -> new GlobalException(ErreurEnum.RESOURCE_NOT_FOUND));
        userExamen.setExamen(examen);
        userExamen.setDateExamen(LocalDateTime.now());
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
        finalizeSaveUserExamen(userExamenDto);
        return userExamenDto;
    }

    private void finalizeSaveUserExamen(UserExamenDto userExamenDto) {
        UserExamen userExamen = userExamenRepository.findById(userExamenDto.getId());
        userExamen.setStatut(UserExamenStatut.Termine);
        userExamen.setNbrQuestions(userExamenDto.getQuestions().size());
        userExamen.setNbrQuestionsCorrectes(userExamenDto.getQuestions().stream().filter(x->x.isCorrecte()).collect(Collectors.toList()).size());
        if(userExamen.getNbrQuestionsCorrectes()* 100/ userExamen.getNbrQuestions() >= userExamen.getExamen().getPourcentage()){
            userExamen.setResultat(true);
        } else {
            userExamen.setResultat(false);
        }
        userExamenRepository.save(userExamen);
    }

    public void saveQuestion(QuestionDto questionDto, Long userExamenId) {
        UserExamenQuestionEntity userExamenQuestionEntity = new UserExamenQuestionEntity();
        UserExamen userExamen = userExamenRepository.findById(userExamenId).orElse(new UserExamen());
        userExamenQuestionEntity.setUserExamen(userExamen);
        userExamenQuestionEntity.setQuestionEntity(QuestionMapper.toEntity(questionDto));
        userExamenQuestionEntity.setReponseCorrecte(updateReponse(questionDto));
        userExamenQuestionRepository.save(userExamenQuestionEntity);
        questionDto.getReponses().forEach(x->saveReponse(x, userExamenQuestionEntity));

    }
    private boolean updateReponse (QuestionDto question) {
        boolean isCorrecte = true;
        for(ReponseDto reponse : question.getReponses()) {
            if(reponse.isCorrecte() && !reponse.isSelectionne()) {
                isCorrecte = false;
            } else if(!reponse.isCorrecte() && reponse.isSelectionne()) {
                isCorrecte = false;
            }
        }
        return isCorrecte;
    }
    public void saveReponse(ReponseDto reponseDto, UserExamenQuestionEntity userExamenQuestion) {
        UserExamenQuestionReponseEntity userExamenQuestionReponseEntity = new UserExamenQuestionReponseEntity();
        userExamenQuestionReponseEntity.setUserExamenQuestionEntity(userExamenQuestion);
        ReponseEntity reponseEntity = ReponseMapper.toEntity(reponseDto);
        userExamenQuestionReponseEntity.setReponseEntity(reponseEntity);
        userExamenQuestionReponseEntity.setSelected(reponseDto.isSelectionne());
        userExamenQuestionReponseRepository.save(userExamenQuestionReponseEntity);
    }

    public UserExamenDto getUserExamen(Long id) {
        UserExamen userExamen = userExamenRepository.findById(id).orElse(new UserExamen());
        return UserExamenMapper.toDto(userExamen);
    }
}
