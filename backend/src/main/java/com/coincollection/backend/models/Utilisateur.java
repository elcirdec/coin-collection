package com.coincollection.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a registered user of the application.
 * Manages authentication and collection ownership.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    @JsonProperty("id")
    private Long id;

    /**
     * Username (unique identifier for login).
     * Example: "coin_collector_42"
     */
    @Column(name = "usr_pseudo", nullable = false, unique = true, length = 50)
    @JsonProperty("username")
    private String pseudo;

    /**
     * Email address (unique, used for registration and password reset).
     * Example: "user@example.com"
     */
    @Column(name = "usr_email", nullable = false, unique = true, length = 150)
    @JsonProperty("email")
    private String email;

    /**
     * Hashed password (NEVER store plain text!).
     * Will be hashed with BCrypt when JWT auth is implemented.
     * 
     * IMPORTANT: @JsonIgnore prevents password from being sent in API responses.
     */
    @Column(name = "usr_mdp", nullable = false, length = 255)
    @JsonIgnore
    private String motDePasse;

    /**
     * User role (for future authorization).
     * Example: "USER", "ADMIN", "MODERATOR"
     * 
     * Note: Not in current database schema, but useful for Spring Security.
     */
    @Transient  // Not stored in DB yet
    @JsonProperty("role")
    private String role = "USER";
}