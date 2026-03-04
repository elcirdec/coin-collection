package com.coincollection.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a specific minting/emission of a coin type.
 * A CoinType (e.g., "2€ France Arbre de vie") can have multiple mintings across different years.
 * 
 * Business Rule: Each minting is unique by [CoinType + Year + MintMark].
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "frappe")
public class Frappe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt_id")
    @JsonProperty("id")
    private Long id;

    /**
     * Year of minting (e.g., 1999, 2023).
     */
    @Column(name = "mt_annee", nullable = false)
    @JsonProperty("year")
    private Integer annee;

    /**
     * Total number of coins minted (e.g., 500,000,000).
     * Used to determine rarity and collector value.
     */
    @Column(name = "mt_tirage")
    @JsonProperty("mintageCount")
    private Long tirage;

    /**
     * Mint mark indicating the production facility.
     * Examples: "A" (Paris), "F" (Stuttgart), "Φ" (Athens)
     * NULL for countries with single mint.
     */
    @Column(name = "mt_atelier", length = 10)
    @JsonProperty("mintMark")
    private String atelier;

    /**
     * Relationship: Many mintings belong to one coin type.
     * Foreign key: ct_id → type_piece(ct_id)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ct_id", nullable = false)
    @JsonProperty("coinType")
    private TypePiece typePiece;
}