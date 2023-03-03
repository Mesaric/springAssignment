package com.ct.springassignmentproj.translation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationService {
    private final static String NEW_TRANSLATION_ADDED_MSG = "Translation with text '%s' added.";
    private final static String INCORRECT_LANGUAGE_CODE_LENGTH = "Language code %s is of length %d (should be 2 characters long).";
    private final static String INCORRECT_TRANSLATION_LENGTH = "Translation %s is of length %d (should be between 1 or 127 characters long).";
    private final static String TRANSLATION_NOT_FOUND_MSG = "No translation of language %s was found";
    private final TranslationRepository translationRepository;

    public String getText(String language){
        return translationRepository.findByLanguage(language.toUpperCase())
                .orElseThrow(() -> new IllegalStateException(String.format(TRANSLATION_NOT_FOUND_MSG, language)))
                .getText();
    }

    public String addTranslation(Translation translation) {
        String sanitisedLanguageCode = translation.getLanguage().trim().toUpperCase();
        if (sanitisedLanguageCode.length() != 2) {
            throw new IllegalStateException(String.format(INCORRECT_LANGUAGE_CODE_LENGTH, sanitisedLanguageCode, sanitisedLanguageCode.length()));
        }
        if (translation.getText().length() == 0 || translation.getText().length() > 127) {
            throw new IllegalStateException(String.format(INCORRECT_TRANSLATION_LENGTH, translation.getLanguage(), translation.getLanguage().length()));
        }

        translationRepository.save(
                new Translation(
                        translation.getText(),
                        translation.getLanguage().toUpperCase()
                ));

        return String.format(NEW_TRANSLATION_ADDED_MSG, translation.getText());
    }
}
