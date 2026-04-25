package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.RequesterRepository;

@Service
@RequiredArgsConstructor
public class RequesterService {

    private RequesterRepository requesterRepository;
}
