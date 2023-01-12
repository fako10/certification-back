package com.example.ceertifications.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_examen_reponse")
public class UserExamenQuestionReponseEntity {

    @SequenceGenerator(
            name = "user_examen_reponse_sequence",
            sequenceName = "user_examen_reponse_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_examen_reponse_sequence"
    )
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_examen_question_id", nullable=false)
    private UserExamenQuestionEntity userExamenQuestionEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="reponse_id", nullable=false)
    private ReponseEntity reponseEntity;

    @Column(name = "selected")
    private boolean selected;

}
