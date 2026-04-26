package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.mapper.RequesterMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.guitoji.sample_control_api.model.Requester;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guitoji.sample_control_api.service.RequesterService;

import java.net.URI;

@RestController
@RequestMapping("/requesters")
@RequiredArgsConstructor
public class RequesterController implements GenericController{

    private final RequesterService requesterService;
    private final RequesterMapper requesterMapper;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid RequesterDTO dto) {
        Requester requester = requesterService.register(requesterMapper.toEntity(dto));
        URI location = generateHeaderLocation(requester.getId());
        return ResponseEntity.created(location).build();
    }
}
