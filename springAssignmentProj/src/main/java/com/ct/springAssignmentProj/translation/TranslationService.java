package com.ct.springAssignmentProj.translation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TranslationService {
    private final TranslationRepository translationRepository;

    public String getText(String language){
        return translationRepository.findByLanguage(language.toUpperCase()).get().getText();
    }
}
