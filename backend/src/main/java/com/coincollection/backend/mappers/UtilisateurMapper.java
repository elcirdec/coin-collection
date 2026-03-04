package com.coincollection.backend.mappers;

import com.coincollection.backend.dto.UtilisateurRequestDTO;
import com.coincollection.backend.dto.UtilisateurResponseDTO;
import com.coincollection.backend.models.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurResponseDTO toResponseDTO(Utilisateur utilisateur) {
        if (utilisateur == null) return null;

        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();
        dto.setId(utilisateur.getId());
        dto.setUsername(utilisateur.getPseudo());
        dto.setEmail(utilisateur.getEmail());
        // NO PASSWORD!

        return dto;
    }

    public Utilisateur toEntity(UtilisateurRequestDTO dto) {
        if (dto == null) return null;

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(dto.getUsername());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getPassword()); // Will be hashed in service

        return utilisateur;
    }
}