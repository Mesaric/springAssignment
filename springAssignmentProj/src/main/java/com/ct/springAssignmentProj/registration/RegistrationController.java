package com.ct.springAssignmentProj.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping("registration")
    public String register(@RequestBody RegistrationRequest request) {
        return  registrationService.register(request);
    }
}
