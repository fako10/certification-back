package com.example.ceertifications.service;

import com.example.ceertifications.dto.CheckoutPayment;
import com.example.ceertifications.entities.Certification;
import com.example.ceertifications.entities.CertificationUser;
import com.example.ceertifications.entities.Payement;
import com.example.ceertifications.entities.Users;
import com.example.ceertifications.repositories.CertificationRepository;
import com.example.ceertifications.repositories.CertificationUserRepository;
import com.example.ceertifications.repositories.PayementRepository;
import com.example.ceertifications.security.service.UserDetailsImpl;
import com.example.ceertifications.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PayementService {

    @Autowired
    PayementRepository payementRepository;

    @Autowired
    CertificationRepository certificationRepository;

    @Autowired
    CertificationUserRepository certificationUserRepository;

    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetails;

    @Transactional
    public void savePayement(CheckoutPayment payementDto) {
        Payement payement = new Payement();
        payement.setPayementDate(LocalDateTime.now());
        payement.setAmount(payementDto.getAmount());
        payement.setLibelle(payementDto.getName());
        payement.setCancelUrl(payementDto.getCancelUrl());
        payement.setSuccessUrl(payementDto.getSuccessUrl());
        payement.setCurrency(payementDto.getCurrency());    
        Certification certification = certificationRepository.findById(payementDto.getCertificationId()).orElse(null);
        Users user  = userDetails.getConnectedUser();
        payement.setCertificationEntity(certification);
        payement.setUserEntity(user);
        payementRepository.save(payement);


        CertificationUser certificationUser = new CertificationUser();
        certificationUser.setUser(user);
        certificationUser.setCertificationEntity(certification);
        certificationUserRepository.save(certificationUser);


    }

}
