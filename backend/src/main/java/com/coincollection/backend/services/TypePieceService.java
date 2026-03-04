package com.coincollection.backend.services;

import com.coincollection.backend.dto.TypePieceResponseDTO;
import com.coincollection.backend.mappers.TypePieceMapper;
import com.coincollection.backend.repositories.TypePieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypePieceService {

    @Autowired
    private TypePieceRepository typePieceRepository;

    @Autowired
    private TypePieceMapper mapper;

    public List<TypePieceResponseDTO> getAllCoinTypes() {
        return typePieceRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TypePieceResponseDTO getCoinTypeById(Long id) {
        return typePieceRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Coin type not found: " + id));
    }
}