package com.coincollection.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurResponseDTO {

    private Long id;
    private String username;
    private String email;
    // NO PASSWORD - Security!
}