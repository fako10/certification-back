package com.example.ceertifications.entities;


import com.example.ceertifications.entities.enums.UserExamenStatut;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_Examen")
public class UserExamen {

    @SequenceGenerator(
            name = "user_Examen_sequence",
            sequenceName = "user_Examen_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_Examen_sequence"
    )
    private long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @ManyToOne
    @JoinColumn(name="examen_id", nullable=false)
    private Examen examen;

    @Column(name = "dateexamen")
    private LocalDate dateExamen;

    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private UserExamenStatut statut;

    @Column(name = "resultat")
    private int resultat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userExamen")
    private Set<ReponseQuestionsEntity> reponseQuestionEntities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userExamen")
    private Set<UserExamenQuestionEntity> questions = new HashSet<>();
}
