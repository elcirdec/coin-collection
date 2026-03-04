package com.coincollection.backend.controllers;

import com.coincollection.backend.dto.UtilisateurRequestDTO;
import com.coincollection.backend.dto.UtilisateurResponseDTO;
import com.coincollection.backend.services.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> getAllUtilisateurs() {
        List<UtilisateurResponseDTO> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurById(@PathVariable Long id) {
        UtilisateurResponseDTO user = utilisateurService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UtilisateurResponseDTO> createUtilisateur(@Valid @RequestBody UtilisateurRequestDTO dto) {
        UtilisateurResponseDTO created = utilisateurService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}