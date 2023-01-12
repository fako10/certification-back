package com.example.ceertifications.repositories;

import com.example.ceertifications.entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification,Long> {

    Optional<Certification> findById(Long id);
}
