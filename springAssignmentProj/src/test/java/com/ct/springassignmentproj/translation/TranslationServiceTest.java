package com.ct.springassignmentproj.translation;

import com.ct.springassignmentproj.util.IsoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class TranslationServiceTest {
    @Mock private TranslationRepository translationRepository;
    @Mock private IsoUtil isoUtil;

    private TranslationService underTest;

    @BeforeEach()
    void setUp(){
        underTest = new TranslationService(translationRepository, isoUtil);
    }

    @Test
    @Disabled
    void canAddTranslation() {
        //given
        Translation translation = new Translation(
                "Pozdravljen svet",
                "sl"
        );

        //when
        underTest.addTranslation(translation);
        //given((underTest.addTranslation(translation))).willReturn());
        //then
        ArgumentCaptor<Translation> translationArgumentCaptor = ArgumentCaptor.forClass(Translation.class);
        verify(translationRepository).save(translationArgumentCaptor.capture());

        Translation capturedTranslation = translationArgumentCaptor.getValue();
        assertThat(capturedTranslation).isEqualTo(translation);
    }

    @Test
    @Disabled
    void willThrowWhenCodeLengthIncorrect() {
        //given
        String INCORRECT_LANGUAGE_CODE_LENGTH = "Language code %s is of length %d (should be 2 characters long).";
        String sanitisedLanguageCode = "SLO".trim().toUpperCase();
        Translation translation = new Translation(
                "Pozdravljen svet",
                sanitisedLanguageCode
        );

        //then
        assertThatThrownBy(() -> underTest.addTranslation(translation))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format(INCORRECT_LANGUAGE_CODE_LENGTH, translation.getLanguage(), translation.getLanguage().length()));

        verify(translationRepository, never()).save(any());
    }

    @Test
    @Disabled
    void canAddSanitizedTranslationCode() {
        //given
        String sanitisedLanguageCode = " sl    ".trim().toUpperCase();
        Translation translation = new Translation(
                "Pozdravljen svet",
                sanitisedLanguageCode
        );

        //when
        underTest.addTranslation(translation);

        //then
        ArgumentCaptor<Translation> translationArgumentCaptor = ArgumentCaptor.forClass(Translation.class);
        verify(translationRepository).save(translationArgumentCaptor.capture());

        Translation capturedTranslation = translationArgumentCaptor.getValue();
        assertThat(capturedTranslation).isEqualTo(translation);
    }


}