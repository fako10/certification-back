package com.example.ceertifications.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "reponse")
public class ReponseEntity {

    @SequenceGenerator(
            name = "reponse_sequence",
            sequenceName = "reponse_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reponse_sequence"
    )
    private long id;
    private String intitule;
    private boolean correcte;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private QuestionEntity questionEntity;


}
