package com.coincollection.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviseResponseDTO {

    private Long id;
    private String code;
    private String name;
    private String symbol;
}