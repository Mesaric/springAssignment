package com.ct.springassignmentproj.model;

import lombok.*;

@Data
public class RegistrationRequest {
    private final String email;
    private final String password;
}
