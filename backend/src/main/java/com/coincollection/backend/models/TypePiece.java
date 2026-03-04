package com.coincollection.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a specific coin design/model for a given country and value.
 * 
 * CRITICAL BUSINESS RULE:
 * Uniqueness = [Country + Value + Design]
 * 
 * Example: "2€ France Arbre de vie" ≠ "2€ France Marie Curie"
 * These are TWO different CoinType entries!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_piece")
public class TypePiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ct_id")
    @JsonProperty("id")
    private Long id;

    /**
     * Name/label of the coin design.
     * Example: "Arbre de vie", "Marie Curie", "Bundesadler"
     */
    @Column(name = "ct_label", nullable = false)
    @JsonProperty("label")
    private String label;

    /**
     * Face value of the coin.
     * Example: 0.01, 0.02, 0.05, 0.10, 0.20, 0.50, 1.00, 2.00
     */
    @Column(name = "ct_valeur", nullable = false)
    @JsonProperty("value")
    private Double valeur;

    /**
     * Issuing country.
     * Example: "France", "Germany", "Italy", "Greece"
     */
    @Column(name = "ct_pays", nullable = false, length = 100)
    @JsonProperty("country")
    private String pays;

    /**
     * Is this a commemorative coin?
     * Commemorative = special edition (different design than regular circulation).
     * Example: "2€ France 2023 Marie Curie" = commemorative
     */
    @Column(name = "ct_commem", nullable = false)
    @JsonProperty("isCommemorative")
    private Boolean estCommemorative = false;

    /**
     * Historical description of the design.
     * Example: "The Tree of Life symbolizes growth and connection between nations"
     */
    @Column(name = "ct_desc", columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    /**
     * Path to obverse (national face) image.
     * Example: "/images/euro/fr-2e-arbre-2001.jpg"
     * 
     * IMPORTANT: Store only the path, NOT the image binary!
     */
    @Column(name = "ct_img_avers")
    @JsonProperty("imageObverse")
    private String imageAvers;

    /**
     * Path to reverse (common face) image.
     * Example: "/images/euro/common-2e.jpg"
     * 
     * Note: For Euro, reverse is identical across all countries for a given value.
     */
    @Column(name = "ct_img_rev")
    @JsonProperty("imageReverse")
    private String imageRevers;

    /**
     * Path to country flag image.
     * Example: "/images/flags/france.png"
     */
    @Column(name = "ct_img_drap")
    @JsonProperty("imageFlag")
    private String imageDrapeau;

    /**
     * Relationship: Many coin types belong to one currency.
     * Foreign key: cur_id → devise(cur_id)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cur_id", nullable = false)
    @JsonProperty("currency")
    private Devise devise;
}