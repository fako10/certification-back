package com.example.ceertifications.repositories;

import com.example.ceertifications.entities.Payement;
import com.example.ceertifications.entities.UserExamenQuestionReponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayementRepository extends JpaRepository<Payement, Long> {
}
