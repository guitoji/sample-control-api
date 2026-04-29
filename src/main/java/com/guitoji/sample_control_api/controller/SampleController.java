package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.controller.mapper.SampleMapper;
import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController implements GenericController{

    private final SampleService sampleService;
    private final SampleMapper sampleMapper;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody SampleDTO dto) {
        Sample sample = sampleService.save(sampleMapper.toEntity(dto));
        URI location = generateHeaderLocation(sample.getId());
        return ResponseEntity.created(location).build();
    }
}
