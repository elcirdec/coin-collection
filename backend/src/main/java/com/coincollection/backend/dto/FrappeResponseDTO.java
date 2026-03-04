package com.coincollection.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrappeResponseDTO {

    private Long id;
    private Integer year;
    private Long mintageCount;
    private String mintMark;
    private CoinTypeSummaryDTO coinType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoinTypeSummaryDTO {
        private Long id;
        private String label;
        private Double value;
        private String country;
    }
}