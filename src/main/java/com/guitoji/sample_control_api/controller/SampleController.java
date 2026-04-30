package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.controller.dto.ResultSampleSearchDTO;
import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.controller.mapper.SampleMapper;
import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResultSampleSearchDTO> search(@PathVariable String id) {
        return sampleService.search(UUID.fromString(id))
                .map(sample -> {
                    ResultSampleSearchDTO dto = sampleMapper.toDto(sample);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return sampleService.search(UUID.fromString(id))
                .map(sample -> {
                    sampleService.delete(sample);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
