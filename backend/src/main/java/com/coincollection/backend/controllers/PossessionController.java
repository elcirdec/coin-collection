package com.coincollection.backend.controllers;

import com.coincollection.backend.dto.PossessionRequestDTO;
import com.coincollection.backend.dto.PossessionResponseDTO;
import com.coincollection.backend.services.PossessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing user coin collections.
 * Handles adding, viewing, and removing coins from user collections.
 */
@RestController
@RequestMapping("/api/collection")
@CrossOrigin(origins = "*")
public class PossessionController {

    @Autowired
    private PossessionService possessionService;

    @GetMapping
    public ResponseEntity<List<PossessionResponseDTO>> getAllPossessions() {
        List<PossessionResponseDTO> possessions = possessionService.getAllPossessions();
        return ResponseEntity.ok(possessions);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PossessionResponseDTO>> getUserCollection(@PathVariable Long userId) {
        List<PossessionResponseDTO> possessions = possessionService.getUserPossessions(userId);
        return ResponseEntity.ok(possessions);
    }

    @GetMapping("/user/{userId}/coin/{coinTypeId}")
    public ResponseEntity<List<PossessionResponseDTO>> getUserCoinPossessions(
            @PathVariable Long userId,
            @PathVariable Long coinTypeId) {
        List<PossessionResponseDTO> possessions = possessionService.getUserCoinPossessions(userId, coinTypeId);
        return ResponseEntity.ok(possessions);
    }

    @GetMapping("/user/{userId}/incomplete")
    public ResponseEntity<List<PossessionResponseDTO>> getIncompletePossessions(@PathVariable Long userId) {
        List<PossessionResponseDTO> possessions = possessionService.getIncompletePossessions(userId);
        return ResponseEntity.ok(possessions);
    }

    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Integer> getUserCoinCount(@PathVariable Long userId) {
        Integer count = possessionService.getUserCoinCount(userId);
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<PossessionResponseDTO> addToCollection(@Valid @RequestBody PossessionRequestDTO dto) {
        PossessionResponseDTO created = possessionService.addPossession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PossessionResponseDTO> updatePossession(
            @PathVariable Long id,
            @Valid @RequestBody PossessionRequestDTO dto) {
        PossessionResponseDTO updated = possessionService.updatePossession(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCollection(@PathVariable Long id) {
        possessionService.deletePossession(id);
        return ResponseEntity.noContent().build();
    }
}