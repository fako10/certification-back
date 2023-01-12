package com.example.ceertifications.repositories;

import com.example.ceertifications.entities.UserExamenQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExamenQuestionRepository  extends JpaRepository<UserExamenQuestionEntity, Long> {
}
