package com.guitoji.sample_control_api.validation;

import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleValidator {

    private final SampleRepository sampleRepository;

    public void validate(Sample sample) {

    }
}
