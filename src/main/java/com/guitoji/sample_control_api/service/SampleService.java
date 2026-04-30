package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public Sample save(Sample sample) {
        return sampleRepository.save(sample);
    }

    public Optional<Sample> search(UUID id) {
        return sampleRepository.findById(id);
    }

    public void delete(Sample sample) {
        sampleRepository.delete(sample);
    }
}
