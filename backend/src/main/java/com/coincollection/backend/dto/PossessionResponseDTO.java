package com.coincollection.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for returning possession data to the client.
 * Used for GET endpoints and POST/PUT responses.
 * 
 * This contains all the information needed to display a possession,
 * including nested data from related entities.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PossessionResponseDTO {

    /**
     * Possession ID.
     */
    private Long id;

    /**
     * Quantity of identical coins.
     */
    private Integer quantity;

    /**
     * Physical condition (UNC, BU, BE, CIRCULATED).
     */
    private String condition;

    /**
     * Date when acquired (business date).
     */
    private LocalDate acquisitionDate;

    /**
     * Date when record was created (system date).
     */
    private LocalDateTime createdAt;

    /**
     * Purchase price.
     */
    private Double purchasePrice;

    /**
     * User who owns this coin.
     */
    private UserSummaryDTO user;

    /**
     * Coin type information.
     */
    private CoinTypeSummaryDTO coinType;

    /**
     * Minting information (year, mint mark).
     * NULL for Quick Add possessions.
     */
    private MintingSummaryDTO minting;

    /**
     * Summary of user info (avoid exposing full user entity).
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummaryDTO {
        private Long id;
        private String username;
    }

    /**
     * Summary of coin type info.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoinTypeSummaryDTO {
        private Long id;
        private String label;
        private Double value;
        private String country;
        private Boolean isCommemorative;
        private String imageObverse;
    }

    /**
     * Summary of minting info.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MintingSummaryDTO {
        private Long id;
        private Integer year;
        private String mintMark;
        private Long mintageCount;
    }
}