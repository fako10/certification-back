package com.example.ceertifications.repositories;


import com.example.ceertifications.entities.Certification;
import com.example.ceertifications.entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {

    List<Examen> findExamenByCertificationEntity(Certification certification);
}
