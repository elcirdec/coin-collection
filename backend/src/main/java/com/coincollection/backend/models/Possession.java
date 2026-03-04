package com.coincollection.backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a coin in a user's collection.
 * 
 * Business Rules:
 * 1. Quick Add: User adds coin without specifying year/condition → frappe=NULL, etat=NULL
 * 2. Expert Add: User selects specific year/mint/condition → all fields filled
 * 3. User can own multiple copies of the same coin (different conditions/years)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "possession")
public class Possession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pos_id")
    @JsonProperty("id")
    private Long id;

    /**
     * Number of identical coins owned (same type, year, condition).
     * Default: 1
     */
    @Column(name = "pos_quantite", nullable = false)
    @JsonProperty("quantity")
    private Integer quantite = 1;

    /**
     * Physical condition of the coin.
     * NULL = not specified (Quick Add mode)
     * Values: UNC, BU, BE, CIRCULATED
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "pos_etat", length = 20)
    @JsonProperty("condition")
    private Condition etat;

    /**
     * Date when the user acquired the coin (business date).
     * NULL = not specified or user doesn't remember.
     * User can fill this retroactively.
     */
    @Column(name = "pos_date_acq")
    @JsonProperty("acquisitionDate")
    private LocalDate dateAcquisition;

    /**
     * System timestamp: when this record was created (audit trail).
     * Automatically set by @PrePersist.
     * Different from acquisitionDate (business date).
     */
    @Column(name = "pos_date_crea", nullable = false, updatable = false)
    @JsonProperty("createdAt")
    private LocalDateTime dateCreation;

    /**
     * Optional: Purchase price paid by user.
     * Useful for tracking collection investment.
     */
    @Column(name = "pos_prix_achat")
    @JsonProperty("purchasePrice")
    private Double prixAchat;

    /**
     * Relationship: Many possessions belong to one user.
     * Foreign key: usr_id → utilisateur(usr_id)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    @JsonProperty("user")
    private Utilisateur utilisateur;

    /**
     * Relationship: Many possessions refer to one coin type.
     * Foreign key: ct_id → type_piece(ct_id)
     * 
     * Example: Multiple users can own "2€ France Arbre de vie"
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ct_id", nullable = false)
    @JsonProperty("coinType")
    private TypePiece typePiece;

    /**
     * Relationship: Many possessions can refer to one specific minting.
     * Foreign key: mt_id → frappe(mt_id)
     * 
     * IMPORTANT: This is NULLABLE for Quick Add mode.
     * - Quick Add: frappe = NULL (user just clicks "Add to collection")
     * - Expert Add: frappe = specific year/mint (user selects "2023, Paris, UNC")
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mt_id")  // Nullable!
    @JsonProperty("minting")
    private Frappe frappe;

    /**
     * JPA lifecycle callback: Set creation timestamp automatically.
     */
    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
    }

    /**
     * Business logic: Check if coin details are fully specified.
     * @return true if both condition and minting are specified
     */
    public boolean isFullySpecified() {
        return etat != null && frappe != null;
    }

    /**
     * Business logic: Check if this is a "Quick Add" possession.
     * @return true if added without specifying year/condition
     */
    public boolean isQuickAdd() {
        return frappe == null;
    }
}