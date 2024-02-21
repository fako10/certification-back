package com.example.ceertifications.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "certification")
public class Certification {

    @SequenceGenerator(
            name = "certification_sequence",
            sequenceName = "certification_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "certification_sequence"
    )
    private long id;
    private String libelle;
    private String description;
    private Float amount;
    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "certificationEntity")
    private Set<Examen> examenEntities = new HashSet<>();*/
}
