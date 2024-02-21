package com.example.ceertifications.mapping;

import com.example.ceertifications.dto.CertificationDto;
import com.example.ceertifications.entities.Certification;

public class CertificationMapping {

    public static CertificationDto EntityToDto(Certification entity) {
        CertificationDto dto = new CertificationDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setLibelle(entity.getLibelle());
        dto.setAmount(entity.getAmount());
        return dto;
    }
}
