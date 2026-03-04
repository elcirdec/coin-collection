package com.coincollection.backend.controllers;

import com.coincollection.backend.dto.TypePieceResponseDTO;
import com.coincollection.backend.services.TypePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
@CrossOrigin(origins = "*")
public class TypePieceController {

    @Autowired
    private TypePieceService typePieceService;

    @GetMapping
    public ResponseEntity<List<TypePieceResponseDTO>> getAllPieces() {
        List<TypePieceResponseDTO> pieces = typePieceService.getAllCoinTypes();
        return ResponseEntity.ok(pieces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePieceResponseDTO> getPieceById(@PathVariable Long id) {
        TypePieceResponseDTO piece = typePieceService.getCoinTypeById(id);
        return ResponseEntity.ok(piece);
    }
}