package com.coincollection.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a currency (e.g., Euro, Dollar, Franc).
 * Each currency can have multiple coin types.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devise")
public class Devise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cur_id")
    @JsonProperty("id")
    private Long id;

    /**
     * ISO currency code (e.g., "EUR", "USD", "CHF").
     * Must be unique.
     */
    @Column(name = "cur_code", unique = true, length = 5, nullable = false)
    @JsonProperty("code")
    private String code;

    /**
     * Full currency name (e.g., "Euro", "Dollar", "Franc suisse").
     */
    @Column(name = "cur_nom", nullable = false, length = 50)
    @JsonProperty("name")
    private String nom;

    /**
     * Currency symbol (e.g., "€", "$", "CHF").
     */
    @Column(name = "cur_symbole", length = 5)
    @JsonProperty("symbol")
    private String symbole;
}