package com.coincollection.backend.services;

import com.coincollection.backend.dto.UtilisateurRequestDTO;
import com.coincollection.backend.dto.UtilisateurResponseDTO;
import com.coincollection.backend.mappers.UtilisateurMapper;
import com.coincollection.backend.models.Utilisateur;
import com.coincollection.backend.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurMapper mapper;

    public List<UtilisateurResponseDTO> getAllUsers() {
        return utilisateurRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UtilisateurResponseDTO getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public UtilisateurResponseDTO createUser(UtilisateurRequestDTO dto) {
        Utilisateur utilisateur = mapper.toEntity(dto);
        
        // TODO: Hash password with BCrypt when implementing security
        // utilisateur.setMotDePasse(passwordEncoder.encode(dto.getPassword()));
        
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return mapper.toResponseDTO(saved);
    }
}