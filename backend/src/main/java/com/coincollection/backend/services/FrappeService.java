package com.coincollection.backend.services;

import com.coincollection.backend.dto.FrappeResponseDTO;
import com.coincollection.backend.mappers.FrappeMapper;
import com.coincollection.backend.repositories.FrappeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FrappeService {

    @Autowired
    private FrappeRepository frappeRepository;

    @Autowired
    private FrappeMapper mapper;

    public List<FrappeResponseDTO> getAllMintings() {
        return frappeRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<FrappeResponseDTO> getMintingsByCoinType(Long coinTypeId) {
        return frappeRepository.findByTypePieceIdOrderByAnneeDesc(coinTypeId)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<FrappeResponseDTO> getMintingsByYear(Integer year) {
        return frappeRepository.findByAnnee(year)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FrappeResponseDTO getMintingById(Long id) {
        return frappeRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Minting not found: " + id));
    }
}