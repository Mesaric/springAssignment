package com.ct.springassignmentproj.service;

import com.ct.springassignmentproj.aop.Log;
import com.ct.springassignmentproj.model.Translation;
import com.ct.springassignmentproj.repository.TranslationRepository;
import com.ct.springassignmentproj.util.IsoUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationService {
    private final static String NEW_TRANSLATION_ADDED_MSG = "Translation with text '%s' added.";
    private final static String INVALID_TEXT_LENGTH_MSG = "Text %s is of length %d (should be between 1 or 127 characters long).";
    private final static String TRANSLATION_NOT_FOUND_MSG = "No translation of language %s was found.";
    private final static String TRANSLATION_EXISTS_MSG = "Translation of language %s already exists.";

    private final TranslationRepository translationRepository;
    private final IsoUtil isoUtil;

    @Log
    public String getText(String language){
        String sanitisedLanguage = isoUtil.sanitizeISOCode(language);
        if (!IsoUtil.isValidISOLanguage(sanitisedLanguage)){
            throw new RuntimeException(String.format(IsoUtil.NOT_ISO_LANGUAGE_CODE, sanitisedLanguage));
        }
        return translationRepository.findByLanguage(sanitisedLanguage)
                .orElseThrow(() -> new IllegalStateException(String.format(TRANSLATION_NOT_FOUND_MSG, language)))
                .getText();
    }

    @Log
    public String addTranslation(Translation translation) {
        String sanitisedLanguage = isoUtil.sanitizeISOCode(translation.getLanguage());

        boolean translationExists = translationRepository.findByLanguage(sanitisedLanguage).isPresent();
        if (translationExists) {
            throw new IllegalStateException(String.format(TRANSLATION_EXISTS_MSG, sanitisedLanguage));
        }

        if (!IsoUtil.isValidISOLanguage(sanitisedLanguage)){
            throw new IllegalStateException(String.format(IsoUtil.NOT_ISO_LANGUAGE_CODE, sanitisedLanguage));
        }

        if (translation.getText().length() == 0 || translation.getText().length() > 127) {
            throw new IllegalStateException(String.format(INVALID_TEXT_LENGTH_MSG, translation.getText(), translation.getText().length()));
        }

        translationRepository.save(
                new Translation(
                        translation.getText(),
                        sanitisedLanguage
                ));

        return String.format(NEW_TRANSLATION_ADDED_MSG, translation.getText());
    }
}
