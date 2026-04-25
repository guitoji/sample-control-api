package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.RequesterService;

@RestController
@RequestMapping("requesters")
@RequiredArgsConstructor
public class RequesterController {

    private RequesterService requesterService;
}
