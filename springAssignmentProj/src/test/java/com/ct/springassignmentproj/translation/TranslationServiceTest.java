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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

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
    public void willAddTranslationWithValidLanguageCode() {
        //given
        final String NEW_TRANSLATION_ADDED_MSG = "Translation with text '%s' added.";
        final String VALID_ISO_LANGUAGE = "en";
        final String TEXT = "test translation";
        Translation translation = new Translation(TEXT, VALID_ISO_LANGUAGE);

        //when
        when(isoUtil.sanitizeISOCode(VALID_ISO_LANGUAGE)).thenReturn(VALID_ISO_LANGUAGE);
        String addedTranslationMSG = underTest.addTranslation(translation);

        //then
        assertEquals(String.format(NEW_TRANSLATION_ADDED_MSG, TEXT), addedTranslationMSG);
        verify(translationRepository, times(1)).save(translation);
    }

    @Test()
    public void willThrowAtAddTranslationWithIncorrectIsoCode() {
        //given
        final String NOT_ISO_LANGUAGE_CODE = "%s is not an ISO language code!";
        final String INVALID_ISO_LANGUAGE = "oo";
        final String TEXT = "test translation";
        Translation translation = new Translation(TEXT, INVALID_ISO_LANGUAGE);

        //when
        when(isoUtil.sanitizeISOCode(INVALID_ISO_LANGUAGE)).thenReturn(INVALID_ISO_LANGUAGE);

        //then
        assertThatThrownBy(() -> underTest.addTranslation(translation))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format(NOT_ISO_LANGUAGE_CODE, translation.getLanguage()));

        verify(translationRepository, never()).save(any());
    }

    @Test()
    public void willThrowAtAddTranslationWithIncorrectTranslationLength() {
        //given
        final String INCORRECT_TRANSLATION_LENGTH_MSG = "Translation %s is of length %d (should be between 1 or 127 characters long).";
        final String VALID_ISO_LANGUAGE = "en";
        final String invalidText = "This text is longer than the maximum allowed length of 127 characters. This text is longer than the maximum allowed length of 127 characters. This text is longer than the maximum allowed length of 127 characters.";
        Translation translation = new Translation(invalidText, VALID_ISO_LANGUAGE);

        //when
        when(isoUtil.sanitizeISOCode(VALID_ISO_LANGUAGE)).thenReturn(VALID_ISO_LANGUAGE);

        //then
        assertThatThrownBy(() -> underTest.addTranslation(translation))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format(INCORRECT_TRANSLATION_LENGTH_MSG, translation.getLanguage(), translation.getLanguage().length()));

        verify(translationRepository, never()).save(any());
    }


}