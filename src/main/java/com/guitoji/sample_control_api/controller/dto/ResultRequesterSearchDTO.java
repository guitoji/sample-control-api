package com.guitoji.sample_control_api.controller.dto;

import com.guitoji.sample_control_api.model.Department;

import java.util.UUID;

public record ResultRequesterSearchDTO(
        UUID id,
        String name,
        String email,
        Department department,
        Integer build
) {
}
