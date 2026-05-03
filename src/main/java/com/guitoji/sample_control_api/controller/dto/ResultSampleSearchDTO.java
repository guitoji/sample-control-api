package com.guitoji.sample_control_api.controller.dto;

import com.guitoji.sample_control_api.model.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record ResultSampleSearchDTO(
        UUID id,
        Integer commodity,
        String description,
        BigDecimal price,
        Integer quantity,
        Status status,
        ResultRequesterSearchDTO resultRequesterSearchDTO
) {

}
