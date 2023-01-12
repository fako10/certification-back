package com.example.ceertifications.repositories;

import com.example.ceertifications.entities.UserExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamenRepository extends JpaRepository<UserExamen,Long> {

    List<UserExamen> findByUserAndExamen(long userId, Long examenId);
    UserExamen findById(long id);

}
