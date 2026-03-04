package com.coincollection.backend.controllers;

import com.coincollection.backend.dto.FrappeResponseDTO;
import com.coincollection.backend.services.FrappeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mintings")
@CrossOrigin(origins = "*")
public class FrappeController {

    @Autowired
    private FrappeService frappeService;

    @GetMapping
    public ResponseEntity<List<FrappeResponseDTO>> getAllMintings() {
        List<FrappeResponseDTO> mintings = frappeService.getAllMintings();
        return ResponseEntity.ok(mintings);
    }

    @GetMapping("/coin/{coinTypeId}")
    public ResponseEntity<List<FrappeResponseDTO>> getMintingsByCoinType(@PathVariable Long coinTypeId) {
        List<FrappeResponseDTO> mintings = frappeService.getMintingsByCoinType(coinTypeId);
        return ResponseEntity.ok(mintings);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<FrappeResponseDTO>> getMintingsByYear(@PathVariable Integer year) {
        List<FrappeResponseDTO> mintings = frappeService.getMintingsByYear(year);
        return ResponseEntity.ok(mintings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrappeResponseDTO> getMintingById(@PathVariable Long id) {
        FrappeResponseDTO minting = frappeService.getMintingById(id);
        return ResponseEntity.ok(minting);
    }
}