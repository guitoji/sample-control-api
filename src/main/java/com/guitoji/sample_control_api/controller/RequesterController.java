package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.model.Department;
import com.guitoji.sample_control_api.service.RequesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/requesters")
@RequiredArgsConstructor
public class RequesterController implements GenericController{

    private final RequesterService requesterService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid RequesterDTO dto) {
        UUID id = requesterService.register(dto);
        URI location = generateHeaderLocation(id);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultRequesterSearchDTO> search(@PathVariable String id) {
        return requesterService.search(UUID.fromString(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        requesterService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid RequesterDTO dto) {
        requesterService.update(UUID.fromString(id), dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResultRequesterSearchDTO>> filter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Department department,
            @RequestParam(required = false) Integer build) {

        return ResponseEntity.ok(requesterService.filterByExample(name, department, build));
    }
}
