package com.ct.springassignmentproj.controller;

import com.ct.springassignmentproj.model.RegistrationRequest;
import com.ct.springassignmentproj.service.RegistrationService;
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
