package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.mapper.RequesterMapper;
import com.guitoji.sample_control_api.model.Department;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import com.guitoji.sample_control_api.validation.RequesterValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequesterService {

    private final RequesterRepository requesterRepository;
    private final RequesterMapper requesterMapper;
    private final RequesterValidation requesterValidation;

    @Transactional
    public UUID register(RequesterDTO dto) {
        Requester requester = requesterMapper.toEntity(dto);
        requesterValidation.validate(requester);
        return requesterRepository.save(requester).getId();
    }

    public Optional<ResultRequesterSearchDTO> search(UUID id) {
        return requesterRepository.findById(id)
                .map(requesterMapper::toDTO);
    }

    @Transactional
    public void delete(UUID id) {
        Requester requester = requesterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requester not found: " + id));
        requesterRepository.delete(requester);
    }

    @Transactional
    public void update(UUID id, RequesterDTO dto) {
        /*
         * implement throw of operationNotAllowed after, one requester cannot be deleted when he has samples linked to him
         */
        Requester requester = requesterRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        requester.setName(dto.name());
        requester.setEmail(dto.email());
        requester.setDepartment(dto.department());
        requester.setBuild(dto.build());

        requesterRepository.save(requester);
    }

    public List<ResultRequesterSearchDTO> filterByExample(String name, Department department, Integer build) {
        var requester = new Requester();
        requester.setName(name);
        requester.setDepartment(department);
        requester.setBuild(build);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withIgnorePaths("id", "email", "samples")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Requester> requesterExample = Example.of(requester, matcher);

        return requesterRepository.findAll(requesterExample)
                .stream()
                .map(requesterMapper::toDTO)
                .toList();
    }

}
