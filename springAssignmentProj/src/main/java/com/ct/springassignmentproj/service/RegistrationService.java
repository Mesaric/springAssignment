package com.ct.springassignmentproj.service;

import com.ct.springassignmentproj.aop.Log;
import com.ct.springassignmentproj.model.AppUser;
import com.ct.springassignmentproj.model.AppUserRole;
import com.ct.springassignmentproj.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final static String EMAIL_NOT_VALID_MSG = "Email %s is not valid";
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    @Log
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(String.format(EMAIL_NOT_VALID_MSG, request.getEmail()));
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
