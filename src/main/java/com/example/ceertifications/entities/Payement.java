package com.example.ceertifications.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payement")
public class Payement {
    @SequenceGenerator(
            name = "payement_sequence",
            sequenceName = "payement_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payement_sequence"
    )
    private long id;

    private String libelle;
    @ManyToOne
    @JoinColumn(name="certification_id", nullable=false)
    private Certification certificationEntity;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Users userEntity;
    private String currency;
    @Column(name = "successurl")
    private String successUrl;
    @Column(name = "cancelurl")
    private String cancelUrl;
    @Column(name = "payement_date")
    private LocalDateTime payementDate;
    private float amount;

}
