package com.guitoji.sample_control_api.controller.dto;

import com.guitoji.sample_control_api.model.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record SampleDTO(
        Integer commodity,
        String description,
        BigDecimal price,
        Status status,
        UUID id_requester
) {
}
