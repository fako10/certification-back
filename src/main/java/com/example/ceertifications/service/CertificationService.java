package com.example.ceertifications.service;

import com.example.ceertifications.dto.CertificationDto;
import com.example.ceertifications.entities.Certification;
import com.example.ceertifications.entities.CertificationUser;
import com.example.ceertifications.entities.Users;
import com.example.ceertifications.mapping.CertificationMapping;
import com.example.ceertifications.repositories.CertificationRepository;
import com.example.ceertifications.repositories.CertificationUserRepository;
import com.example.ceertifications.repositories.UserRepository;
import com.example.ceertifications.security.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CertificationService {


    private CertificationUserRepository certificationUserRepository;
    private UserRepository userRepository;
    private CertificationRepository certificationRepository;

    public List<CertificationDto> getCertificationByUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)auth.getPrincipal();
        List<Certification> certificationEntities = new ArrayList<>();
        Optional<Users> user = userRepository.findById(userDetails.getId());
        List<CertificationUser> entities = certificationUserRepository.findCertificationUserByUser(user.get());
        certificationEntities = entities.stream().map(x->x.getCertificationEntity()).collect(Collectors.toList());

        return certificationEntities.stream().map(x->CertificationMapping.EntityToDto(x)).collect(Collectors.toList());
    }

    public CertificationDto getCertificationById(Long certificationId) {
        return CertificationMapping.EntityToDto(certificationRepository.findById(certificationId).orElse(new Certification()));
    }

    public CertificationDto getCertificationByLibelle(String libelle) {
        return CertificationMapping.EntityToDto(certificationRepository.findCertificationByLibelle(libelle).orElse(new Certification()));
    }

}
