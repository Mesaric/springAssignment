package com.ct.springAssignmentProj.translation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationService {
    private final static String NEW_TRANSLATION_ADDED_MSG = "Translation with text '%s' added.";
    private final static String NEW_TRANSLATION_ADDED_UNSUCCESSFULLY_MSG = "Translation with text '%s' of language '%s' could not be added.";
    private final TranslationRepository translationRepository;

    public String getText(String language){
        return translationRepository.findByLanguage(language.toUpperCase()).get().getText();
    }

    public String addTranslation(Translation translation){
        //todo: entry validation, UI warning for input
        try {
            translationRepository.save(
                new Translation(
                        translation.getText(),
                        translation.getLanguage().toUpperCase()
                ));

            return String.format(NEW_TRANSLATION_ADDED_MSG, translation.getText());

        }
        catch (Exception exception) {
            return String.format(NEW_TRANSLATION_ADDED_UNSUCCESSFULLY_MSG, translation.getText(), translation.getLanguage().toUpperCase());
        }
    }
}
