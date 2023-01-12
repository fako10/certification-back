package com.example.ceertifications.repositories;

import com.example.ceertifications.entities.CertificationUser;
import com.example.ceertifications.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationUserRepository extends JpaRepository<CertificationUser,Long> {

    List<CertificationUser> findCertificationUserByUser(Users user);
}
