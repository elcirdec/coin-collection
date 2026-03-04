package com.coincollection.backend.mappers;

import com.coincollection.backend.dto.TypePieceResponseDTO;
import com.coincollection.backend.models.TypePiece;
import org.springframework.stereotype.Component;

@Component
public class TypePieceMapper {

    public TypePieceResponseDTO toResponseDTO(TypePiece typePiece) {
        if (typePiece == null) return null;

        TypePieceResponseDTO dto = new TypePieceResponseDTO();
        dto.setId(typePiece.getId());
        dto.setLabel(typePiece.getLabel());
        dto.setValue(typePiece.getValeur());
        dto.setCountry(typePiece.getPays());
        dto.setIsCommemorative(typePiece.getEstCommemorative());
        dto.setDescription(typePiece.getDescription());
        dto.setImageObverse(typePiece.getImageAvers());
        dto.setImageReverse(typePiece.getImageRevers());
        dto.setImageFlag(typePiece.getImageDrapeau());

        if (typePiece.getDevise() != null) {
            TypePieceResponseDTO.CurrencySummaryDTO currencyDTO = 
                new TypePieceResponseDTO.CurrencySummaryDTO();
            currencyDTO.setId(typePiece.getDevise().getId());
            currencyDTO.setCode(typePiece.getDevise().getCode());
            currencyDTO.setName(typePiece.getDevise().getNom());
            currencyDTO.setSymbol(typePiece.getDevise().getSymbole());
            dto.setCurrency(currencyDTO);
        }

        return dto;
    }
}