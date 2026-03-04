package com.coincollection.backend.mappers;

import com.coincollection.backend.dto.DeviseResponseDTO;
import com.coincollection.backend.models.Devise;
import org.springframework.stereotype.Component;

@Component
public class DeviseMapper {

    public DeviseResponseDTO toResponseDTO(Devise devise) {
        if (devise == null) return null;

        DeviseResponseDTO dto = new DeviseResponseDTO();
        dto.setId(devise.getId());
        dto.setCode(devise.getCode());
        dto.setName(devise.getNom());
        dto.setSymbol(devise.getSymbole());

        return dto;
    }
}