package com.guitoji.sample_control_api.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorWarningDTO(int status, String message, List<FieldWarningDTO> fieldWarning) {

    public static ErrorWarningDTO entityNotFound(String message) {
        return new ErrorWarningDTO(HttpStatus.NOT_FOUND.value(), message, List.of());
    }

    public static ErrorWarningDTO badRequest(String message) {
        return new ErrorWarningDTO(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorWarningDTO conflict(String message) {
        return new ErrorWarningDTO(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
