package com.coincollection.backend.mappers;

import com.coincollection.backend.dto.FrappeResponseDTO;
import com.coincollection.backend.models.Frappe;
import org.springframework.stereotype.Component;

@Component
public class FrappeMapper {

    public FrappeResponseDTO toResponseDTO(Frappe frappe) {
        if (frappe == null) return null;

        FrappeResponseDTO dto = new FrappeResponseDTO();
        dto.setId(frappe.getId());
        dto.setYear(frappe.getAnnee());
        dto.setMintageCount(frappe.getTirage());
        dto.setMintMark(frappe.getAtelier());

        if (frappe.getTypePiece() != null) {
            FrappeResponseDTO.CoinTypeSummaryDTO coinTypeDTO = 
                new FrappeResponseDTO.CoinTypeSummaryDTO();
            coinTypeDTO.setId(frappe.getTypePiece().getId());
            coinTypeDTO.setLabel(frappe.getTypePiece().getLabel());
            coinTypeDTO.setValue(frappe.getTypePiece().getValeur());
            coinTypeDTO.setCountry(frappe.getTypePiece().getPays());
            dto.setCoinType(coinTypeDTO);
        }

        return dto;
    }
}