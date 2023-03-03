package com.ct.springassignmentproj.util;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

@Service
@AllArgsConstructor
public class IsoUtil {
    private static final Set<String> ISO_LANGUAGES = Set.of(Locale.getISOLanguages());
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());
    public static final String NOT_ISO_LANGUAGE_CODE = "%s is not an ISO language code!";

    public static boolean isValidISOLanguage(String s) {
        return ISO_LANGUAGES.contains(s);
    }
    public static boolean isValidISOCountry(String s) {
        return ISO_COUNTRIES.contains(s);
    }

    public String sanitizeISOCode(String s){
        return s.toLowerCase().trim();
    }
}