package com.guitoji.sample_control_api.controller.dto;

import com.guitoji.sample_control_api.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record SampleDTO(
        @NotNull(message = "campo obrigatório")
        Integer commodity,
        @NotBlank(message = "campo obrigatório")
        String description,
        @NotNull(message = "campo obrigatório")
        BigDecimal price,
        @NotNull(message = "campo obrigatório")
        Integer quantity,
        @NotNull(message = "campo obrigatório")
        Status status,
        @NotNull(message = "campo obrigatório")
        UUID id_requester
) {

}
