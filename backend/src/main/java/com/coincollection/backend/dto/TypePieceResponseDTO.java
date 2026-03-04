package com.coincollection.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePieceResponseDTO {

    private Long id;
    private String label;
    private Double value;
    private String country;
    private Boolean isCommemorative;
    private String description;
    private String imageObverse;
    private String imageReverse;
    private String imageFlag;
    private CurrencySummaryDTO currency;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrencySummaryDTO {
        private Long id;
        private String code;
        private String name;
        private String symbol;
    }
}