package com.guitoji.sample_control_api.controller;

import com.guitoji.sample_control_api.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController implements GenericController{

    private final SampleService sampleService;
}
