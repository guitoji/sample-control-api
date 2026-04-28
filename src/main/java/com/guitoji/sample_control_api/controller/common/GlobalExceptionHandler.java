package com.guitoji.sample_control_api.controller.common;

import com.guitoji.sample_control_api.controller.dto.ErrorWarningDTO;
import com.guitoji.sample_control_api.controller.dto.FieldWarningDTO;
import com.guitoji.sample_control_api.exception.DuplicatedRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorWarningDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldError = e.getFieldErrors();
        List<FieldWarningDTO> fieldWarningDTOList = fieldError
                .stream()
                .map(field -> new FieldWarningDTO(field.getField(), field.getDefaultMessage()))
                .toList();
        return new ErrorWarningDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro validação", fieldWarningDTOList);
    }

    @ExceptionHandler(DuplicatedRegisterException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorWarningDTO handleDuplicatedRegisterException(DuplicatedRegisterException e) {
        return ErrorWarningDTO.conflict(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorWarningDTO handleRuntimeException(RuntimeException e) {
        return new ErrorWarningDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error ocurred... please contact the administration",
                List.of());
    }

    /*
     * Implement exception handler to Operation not allowed after sample repository is created
     */
}
