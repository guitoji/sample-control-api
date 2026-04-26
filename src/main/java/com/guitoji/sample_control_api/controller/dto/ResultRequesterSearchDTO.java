package com.guitoji.sample_control_api.controller.dto;

import com.guitoji.sample_control_api.model.Sample;

import java.util.List;
import java.util.UUID;

public record ResultRequesterSearchDTO(
        UUID id,
        String name,
        String email,
        String department,
        Integer build
) {
}
