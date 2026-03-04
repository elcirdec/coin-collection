package com.coincollection.backend.services;

import com.coincollection.backend.dto.PossessionRequestDTO;
import com.coincollection.backend.dto.PossessionResponseDTO;
import com.coincollection.backend.mappers.PossessionMapper;
import com.coincollection.backend.models.*;
import com.coincollection.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PossessionService {

    @Autowired
    private PossessionRepository possessionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private TypePieceRepository typePieceRepository;

    @Autowired
    private FrappeRepository frappeRepository;

    @Autowired
    private PossessionMapper mapper;

    /**
     * Get all possessions.
     */
    public List<PossessionResponseDTO> getAllPossessions() {
        return possessionRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get possessions for a specific user.
     */
    public List<PossessionResponseDTO> getUserPossessions(Long userId) {
        return possessionRepository.findByUtilisateurId(userId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get user possessions of a specific coin type.
     */
    public List<PossessionResponseDTO> getUserCoinPossessions(Long userId, Long coinTypeId) {
        return possessionRepository.findByUtilisateurIdAndTypePieceId(userId, coinTypeId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get incomplete possessions (Quick Add).
     */
    public List<PossessionResponseDTO> getIncompletePossessions(Long userId) {
        return possessionRepository.findIncompleteByUserId(userId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get total coin count for a user.
     */
    public Integer getUserCoinCount(Long userId) {
        return possessionRepository.getTotalCoinCount(userId);
    }

    /**
     * Add a new possession to user's collection.
     */
    public PossessionResponseDTO addPossession(PossessionRequestDTO dto) {
        // Create new possession entity
        Possession possession = mapper.toEntity(dto);

        // Load and set user
        Utilisateur user = utilisateurRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.getUserId()));
        possession.setUtilisateur(user);

        // Load and set coin type
        TypePiece coinType = typePieceRepository.findById(dto.getCoinTypeId())
                .orElseThrow(() -> new RuntimeException("Coin type not found: " + dto.getCoinTypeId()));
        possession.setTypePiece(coinType);

        // Load and set minting (optional)
        if (dto.getMintingId() != null) {
            Frappe minting = frappeRepository.findById(dto.getMintingId())
                    .orElseThrow(() -> new RuntimeException("Minting not found: " + dto.getMintingId()));
            possession.setFrappe(minting);
        }

        // Save
        Possession saved = possessionRepository.save(possession);

        // Return DTO
        return mapper.toResponseDTO(saved);
    }

    /**
     * Update an existing possession.
     */
    public PossessionResponseDTO updatePossession(Long id, PossessionRequestDTO dto) {
        // Find existing
        Possession existing = possessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Possession not found: " + id));

        // Update fields
        mapper.updateEntity(existing, dto);

        // Update minting if provided
        if (dto.getMintingId() != null) {
            Frappe minting = frappeRepository.findById(dto.getMintingId())
                    .orElseThrow(() -> new RuntimeException("Minting not found: " + dto.getMintingId()));
            existing.setFrappe(minting);
        } else {
            existing.setFrappe(null);
        }

        // Save
        Possession saved = possessionRepository.save(existing);

        // Return DTO
        return mapper.toResponseDTO(saved);
    }

    /**
     * Delete a possession.
     */
    public void deletePossession(Long id) {
        if (!possessionRepository.existsById(id)) {
            throw new RuntimeException("Possession not found: " + id);
        }
        possessionRepository.deleteById(id);
    }
}