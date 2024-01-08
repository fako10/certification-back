package com.example.ceertifications.controller;

import com.example.ceertifications.dto.ExamenDto;
import com.example.ceertifications.dto.ExamenGroupDto;
import com.example.ceertifications.dto.UserExamenDto;
import com.example.ceertifications.entities.Certification;
import com.example.ceertifications.entities.Examen;
import com.example.ceertifications.entities.ReponseQuestionsEntity;
import com.example.ceertifications.entities.UserExamen;
import com.example.ceertifications.exception.GlobalException;
import com.example.ceertifications.service.CertificationService;
import com.example.ceertifications.service.ExamenService;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ExamenController {

    CertificationService certificationService;
    ExamenService examenService;

    @GetMapping("/certifications")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<Certification> getCertification() {
        return certificationService.getCertificationByUser();
    }

    @GetMapping("/examens/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public List<ExamenDto> getExamenByCertificationId(@PathVariable Long id) {
        return examenService.getExamenByCertificationId(id);
    }

    @GetMapping("/userExamen/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public UserExamenDto getUserExamenById(@PathVariable Long id) {
        return examenService.getUserExamen(id);
    }

    @GetMapping("/examen/{id}")
    //@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ExamenDto getExamenById(@PathVariable Long id) {
        return examenService.getExamenById(id);
    }

    @GetMapping("/examengroups/{id}")
    public List<ExamenGroupDto> getExamenGroupByCertificationId(@PathVariable Long id) {
        return examenService.getExamenGroupByCertificationId(id);
    }

    @GetMapping("/examens/create-user-examen/{id}")
    public UserExamenDto createUserExamen(@PathVariable Long id) throws GlobalException {
        return examenService.createUserExamen(id);
    }

    @PostMapping("/examens/saveExamen")
    public UserExamenDto saveUserExamen(@RequestBody UserExamenDto userExamenDto) {
        return examenService.saveUserExamen(userExamenDto);
    }

    @GetMapping("/examens/get-user-examen/{id}")
    public UserExamenDto getUserExamen(@PathVariable Long id) throws GlobalException {
        return examenService.getUserExamen(id);
    }

}
