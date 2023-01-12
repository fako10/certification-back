package com.example.ceertifications.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reponse_question")
public class ReponseQuestionsEntity {

    @SequenceGenerator(
            name = "Reponse_question_sequence",
            sequenceName = "Reponse_question_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Reponse_question_sequence"
    )
    private long id;
    @ManyToOne
    @JoinColumn(name="user_Examen_id", nullable=false)
    private UserExamen userExamen;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private QuestionEntity questionEntity;

    @ManyToOne
    @JoinColumn(name="reponse_id", nullable=false)
    private ReponseEntity reponseEntity;

}
