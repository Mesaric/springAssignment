package com.ct.springassignmentproj.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    private final static String EMAIL_FORMAT_INVALID_MSG = "Email format %s not valid";

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    @Override
    public boolean test(String email) {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean passedValidation = EmailValidator.patternMatches(email, regexPattern);
        if (!passedValidation) {
            throw new IllegalStateException(String.format(EMAIL_FORMAT_INVALID_MSG, email));
        }
        return true;
    }
}
