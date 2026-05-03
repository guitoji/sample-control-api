package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.controller.dto.ResultSampleSearchDTO;
import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.exception.OperationNotAllowedException;
import com.guitoji.sample_control_api.mapper.SampleMapper;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.model.Status;
import com.guitoji.sample_control_api.repository.SampleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleMapper sampleMapper;
    private final RequesterService requesterService;


    @Transactional
    public UUID register(SampleDTO dto) {
        Sample sample = sampleMapper.toEntity(dto);
        return sampleRepository.save(sample).getId();
    }

    @Transactional(readOnly = true)
    public Optional<ResultSampleSearchDTO> search(UUID id) {
        return sampleRepository.findById(id)
                .map(sampleMapper::toDTO);
    }

    @Transactional
    public void delete(UUID id) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sample not found: " + id));
        if (!sampleProcessed(sample)) {
            throw new OperationNotAllowedException("Not allowed to delete a sample that is not in pending status");
        }
        sampleRepository.delete(sample);
    }

    @Transactional
    public void update(UUID id, SampleDTO dto) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sample not found: " + id));

        Requester requester = requesterService.searchReturningRequester(id);

        sample.setCommodity(dto.commodity());
        sample.setDescription(dto.description());
        sample.setPrice(dto.price());
        sample.setStatus(dto.status());
        sample.setRequester(requester);

        sampleRepository.save(sample);
    }

    @Transactional(readOnly = true)
    public Page<ResultSampleSearchDTO> filterByExample
            (Integer commodity, String description, Status status, Integer page, Integer pageSize) {
        var sample = new Sample();
        sample.setCommodity(commodity);
        sample.setDescription(description);
        sample.setStatus(status);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "price", "requester")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Sample> sampleExample = Example.of(sample, matcher);

        Pageable pageRequest = PageRequest.of(page, pageSize);

        return sampleRepository
                .findAll(sampleExample, pageRequest)
                .map(sampleMapper::toDTO);
    }

    private boolean sampleProcessed(Sample sample) {
        return !sample.getStatus().equals(Status.PENDING);
    }
}

