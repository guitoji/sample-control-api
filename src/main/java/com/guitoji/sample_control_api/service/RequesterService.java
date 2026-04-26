package com.guitoji.sample_control_api.service;

import lombok.RequiredArgsConstructor;
import com.guitoji.sample_control_api.model.Requester;
import org.springframework.stereotype.Service;
import com.guitoji.sample_control_api.repository.RequesterRepository;

@Service
@RequiredArgsConstructor
public class RequesterService {

    private final RequesterRepository requesterRepository;

    public Requester register(Requester requester) {
        return requesterRepository.save(requester);
    }
}
