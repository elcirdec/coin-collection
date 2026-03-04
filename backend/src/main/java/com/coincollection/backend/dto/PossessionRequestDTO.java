package com.coincollection.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * DTO for creating or updating a possession.
 * Used for POST /api/collection and PUT /api/collection/{id}
 * 
 * This separates the API contract from the database entity structure.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PossessionRequestDTO {

    /**
     * User ID who owns the coin.
     * Required for creation.
     */
    @NotNull(message = "User ID is required")
    private Long userId;

    /**
     * Coin type ID.
     * Required for creation.
     */
    @NotNull(message = "Coin type ID is required")
    private Long coinTypeId;

    /**
     * Specific minting ID (year/mint mark).
     * Optional - NULL for Quick Add mode.
     */
    private Long mintingId;

    /**
     * Quantity of identical coins.
     * Must be at least 1.
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    /**
     * Physical condition of the coin.
     * Values: UNC, BU, BE, CIRCULATED
     * Optional - NULL for Quick Add mode.
     */
    private String condition;

    /**
     * Date when the user acquired the coin.
     * Optional - NULL if user doesn't remember.
     */
    private LocalDate acquisitionDate;

    /**
     * Purchase price paid by the user.
     * Optional - for investment tracking.
     */
    private Double purchasePrice;
}