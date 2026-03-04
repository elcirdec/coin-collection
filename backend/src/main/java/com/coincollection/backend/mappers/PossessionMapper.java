package com.coincollection.backend.mappers;

import com.coincollection.backend.dto.PossessionRequestDTO;
import com.coincollection.backend.dto.PossessionResponseDTO;
import com.coincollection.backend.models.*;
import org.springframework.stereotype.Component;

@Component
public class PossessionMapper {

    public PossessionResponseDTO toResponseDTO(Possession possession) {
        if (possession == null) return null;

        PossessionResponseDTO dto = new PossessionResponseDTO();
        dto.setId(possession.getId());
        dto.setQuantity(possession.getQuantite());
        dto.setCondition(possession.getEtat() != null ? possession.getEtat().name() : null);
        dto.setAcquisitionDate(possession.getDateAcquisition());
        dto.setCreatedAt(possession.getDateCreation());
        dto.setPurchasePrice(possession.getPrixAchat());

        // Map user
        if (possession.getUtilisateur() != null) {
            PossessionResponseDTO.UserSummaryDTO userDTO = new PossessionResponseDTO.UserSummaryDTO();
            userDTO.setId(possession.getUtilisateur().getId());
            userDTO.setUsername(possession.getUtilisateur().getPseudo());
            dto.setUser(userDTO);
        }

        // Map coin type
        if (possession.getTypePiece() != null) {
            PossessionResponseDTO.CoinTypeSummaryDTO coinTypeDTO = new PossessionResponseDTO.CoinTypeSummaryDTO();
            coinTypeDTO.setId(possession.getTypePiece().getId());
            coinTypeDTO.setLabel(possession.getTypePiece().getLabel());
            coinTypeDTO.setValue(possession.getTypePiece().getValeur());
            coinTypeDTO.setCountry(possession.getTypePiece().getPays());
            coinTypeDTO.setIsCommemorative(possession.getTypePiece().getEstCommemorative());
            coinTypeDTO.setImageObverse(possession.getTypePiece().getImageAvers());
            dto.setCoinType(coinTypeDTO);
        }

        // Map minting (optional)
        if (possession.getFrappe() != null) {
            PossessionResponseDTO.MintingSummaryDTO mintingDTO = new PossessionResponseDTO.MintingSummaryDTO();
            mintingDTO.setId(possession.getFrappe().getId());
            mintingDTO.setYear(possession.getFrappe().getAnnee());
            mintingDTO.setMintMark(possession.getFrappe().getAtelier());
            mintingDTO.setMintageCount(possession.getFrappe().getTirage());
            dto.setMinting(mintingDTO);
        }

        return dto;
    }

    public Possession toEntity(PossessionRequestDTO dto) {
        if (dto == null) return null;

        Possession possession = new Possession();
        possession.setQuantite(dto.getQuantity());
        possession.setDateAcquisition(dto.getAcquisitionDate());
        possession.setPrixAchat(dto.getPurchasePrice());

        if (dto.getCondition() != null && !dto.getCondition().isEmpty()) {
            try {
                possession.setEtat(Condition.valueOf(dto.getCondition()));
            } catch (IllegalArgumentException e) {
                possession.setEtat(null);
            }
        }

        return possession;
    }

    public void updateEntity(Possession existing, PossessionRequestDTO dto) {
        if (existing == null || dto == null) return;

        existing.setQuantite(dto.getQuantity());
        existing.setDateAcquisition(dto.getAcquisitionDate());
        existing.setPrixAchat(dto.getPurchasePrice());

        if (dto.getCondition() != null && !dto.getCondition().isEmpty()) {
            try {
                existing.setEtat(Condition.valueOf(dto.getCondition()));
            } catch (IllegalArgumentException e) {
                existing.setEtat(null);
            }
        } else {
            existing.setEtat(null);
        }
    }
}