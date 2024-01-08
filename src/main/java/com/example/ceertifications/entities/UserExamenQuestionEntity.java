package com.example.ceertifications.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_examen_question")
public class UserExamenQuestionEntity {

    @SequenceGenerator(
            name = "user_examen_question_sequence",
            sequenceName = "user_examen_question_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_examen_question_sequence"
    )
    private long id;

    @Column(name = "reponse_correcte")
    private boolean reponseCorrecte;

    @ManyToOne
    @JoinColumn(name="user_Examen_id", nullable=false)
    private UserExamen userExamen;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private QuestionEntity questionEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userExamenQuestionEntity")
    private Set<UserExamenQuestionReponseEntity> questions = new HashSet<>();

}
