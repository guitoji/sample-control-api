package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.controller.dto.ResultSampleSearchDTO;
import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.mapper.SampleMapper;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import com.guitoji.sample_control_api.repository.SampleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleMapper sampleMapper;
    private final RequesterRepository requesterRepository;

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
        sampleRepository.delete(sample);
    }

    @Transactional
    public void update(UUID id, SampleDTO dto) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sample not found: " + id));

        Requester requester = requesterRepository.findById(dto.id_requester())
                .orElseThrow(() -> new EntityNotFoundException("Requester not found: " + dto.id_requester()));

        sample.setCommodity(dto.commodity());
        sample.setDescription(dto.description());
        sample.setPrice(dto.price());
        sample.setStatus(dto.status());
        sample.setRequester(requester);

        sampleRepository.save(sample);
    }
}
