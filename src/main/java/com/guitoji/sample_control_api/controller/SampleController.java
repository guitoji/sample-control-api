package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.controller.dto.ResultSampleSearchDTO;
import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.service.SampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController implements GenericController {

    private final SampleService sampleService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid SampleDTO dto) {
        UUID id = sampleService.register(dto);
        URI location = generateHeaderLocation(id);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultSampleSearchDTO> search(@PathVariable String id) {
        return sampleService.search(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        sampleService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid SampleDTO dto) {
        sampleService.update(UUID.fromString(id), dto);
        return ResponseEntity.noContent().build();
    }
}
