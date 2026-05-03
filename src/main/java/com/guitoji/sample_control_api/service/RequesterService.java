package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.exception.OperationNotAllowedException;
import com.guitoji.sample_control_api.mapper.RequesterMapper;
import com.guitoji.sample_control_api.model.Department;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import com.guitoji.sample_control_api.repository.SampleRepository;
import com.guitoji.sample_control_api.validation.RequesterValidator;
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
    private final RequesterValidator requesterValidator;
    private final SampleRepository sampleRepository;

    @Transactional
    public UUID register(RequesterDTO dto) {
        Requester requester = requesterMapper.toEntity(dto);
        requesterValidator.validate(requester);
        return requesterRepository.save(requester).getId();
    }

    @Transactional(readOnly = true)
    public Optional<ResultRequesterSearchDTO> search(UUID id) {
        return requesterRepository.findById(id)
                .map(requesterMapper::toDTO);
    }

    public Requester searchReturningRequester(UUID id) {
        return requesterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requester not found: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        Requester requester = requesterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requester not found: " + id));
        if (hasSamples(requester)) {
            throw new OperationNotAllowedException("Not allowed to delete a requester who has samples");
        }
        requesterRepository.delete(requester);
    }

    @Transactional
    public void update(UUID id, RequesterDTO dto) {
        Requester requester = requesterRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        requester.setName(dto.name());
        requester.setEmail(dto.email());
        requester.setDepartment(dto.department());
        requester.setBuild(dto.build());

        requesterRepository.save(requester);
    }

    @Transactional(readOnly = true)
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

    private boolean hasSamples(Requester requester) {
        return sampleRepository.existsByRequester(requester);
    }
}
