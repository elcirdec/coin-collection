package com.coincollection.backend.services;

import com.coincollection.backend.dto.DeviseResponseDTO;
import com.coincollection.backend.mappers.DeviseMapper;
import com.coincollection.backend.repositories.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviseService {

    @Autowired
    private DeviseRepository deviseRepository;

    @Autowired
    private DeviseMapper mapper;

    public List<DeviseResponseDTO> getAllCurrencies() {
        return deviseRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public DeviseResponseDTO getCurrencyById(Long id) {
        return deviseRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Currency not found: " + id));
    }
}