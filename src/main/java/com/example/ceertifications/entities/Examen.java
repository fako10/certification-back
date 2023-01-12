package com.example.ceertifications.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "examen")
public class Examen {

    @SequenceGenerator(
            name = "examen_sequence",
            sequenceName = "examen_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "examen_sequence"
    )
    private long id;
    private String libelle;
    private String description;
    @ManyToOne
    @JoinColumn(name="certification_id", nullable=false)
    private Certification certificationEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "examen")
    private Set<QuestionEntity> questionEntities = new HashSet<>();
}
