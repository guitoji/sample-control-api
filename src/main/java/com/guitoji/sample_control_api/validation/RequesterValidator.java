package com.guitoji.sample_control_api.validation;

import com.guitoji.sample_control_api.exception.DuplicatedRegisterException;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequesterValidator {

    private final RequesterRepository requesterRepository;

    public void validate(Requester requester) {
        if (requesterAlreadyRegistered(requester)) {
            throw new DuplicatedRegisterException("Requester is already registered.");
        }


    }

    private boolean requesterAlreadyRegistered(Requester requester) {
        Optional<Requester> optionalRequester = requesterRepository
                .findByNameAndEmail(requester.getName(), requester.getEmail());

        if (requester.getId() == null) {
            return optionalRequester.isPresent();
        }

        return !requester.getId().equals(optionalRequester.get().getId()) && optionalRequester.isPresent();
    }
}
