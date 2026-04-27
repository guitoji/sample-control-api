package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.controller.mapper.RequesterMapper;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.service.RequesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResultRequesterSearchDTO> search(@PathVariable String id) {
        return requesterService.search(UUID.fromString(id))
                .map(requester -> {
                    ResultRequesterSearchDTO dto = requesterMapper.toDTO(requester);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return requesterService.search(UUID.fromString(id))
                .map(requester -> {
                    requesterService.delete(requester);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody RequesterDTO dto) {
        return requesterService.search(UUID.fromString(id))
                .map(requester -> {
                    requester.setName(dto.name());
                    requester.setEmail(dto.email());
                    requester.setDepartment(dto.department());
                    requester.setBuild(dto.build());

                    requesterService.update(requester);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
