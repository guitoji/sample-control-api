package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
}
