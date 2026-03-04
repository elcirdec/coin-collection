package com.coincollection.backend.controllers;

import com.coincollection.backend.dto.DeviseResponseDTO;
import com.coincollection.backend.services.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devises")
@CrossOrigin(origins = "*")
public class DeviseController {

    @Autowired
    private DeviseService deviseService;

    @GetMapping
    public ResponseEntity<List<DeviseResponseDTO>> getAllDevises() {
        List<DeviseResponseDTO> devises = deviseService.getAllCurrencies();
        return ResponseEntity.ok(devises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviseResponseDTO> getDeviseById(@PathVariable Long id) {
        DeviseResponseDTO devise = deviseService.getCurrencyById(id);
        return ResponseEntity.ok(devise);
    }
}