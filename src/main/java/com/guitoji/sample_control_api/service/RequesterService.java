package com.guitoji.sample_control_api.service;

import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void delete(Requester requester) {
        requesterRepository.delete(requester);
    }

    public Requester update(Requester requester) {
        return requesterRepository.save(requester);
    }

    public List<Requester> filterByExample(String name , String department, Integer build) {
        var requester = new Requester();
        requester.setName(name);
        requester.setDepartment(department);
        requester.setBuild(build);

        ExampleMatcher matcher =ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withIgnorePaths("id", "email", "samples")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Requester> requesterExample = Example.of(requester, matcher);
        return requesterRepository.findAll(requesterExample);
    }
}
