package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequesterService {

    private final RequesterRepository requesterRepository;

    public Requester register(Requester requester) {
        return requesterRepository.save(requester);
    }

    public Optional<Requester> search(UUID id) {
        return requesterRepository.findById(id);
    }
}
