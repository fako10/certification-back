package com.example.ceertifications.repositories;

import com.example.ceertifications.entities.Examen;
import com.example.ceertifications.entities.UserExamen;
import com.example.ceertifications.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExamenRepository extends JpaRepository<UserExamen,Long> {

    List<UserExamen> findByUserAndExamen(Users user, Examen examenId);
    UserExamen findById(long id);


}
