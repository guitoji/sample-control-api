package com.guitoji.sample_control_api.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequesterDTO(
        @NotBlank(message = "campo obrigatório")
        String name,
        @Email(message = "Informação não correspondente ao formato e-mail")
        @NotBlank(message = "campo obrigatório")
        String email,
        @NotBlank(message = "campo obrigatório")
        String department,
        @NotNull(message = "campo obrigatório")
        Integer build
) {
}
