package com.ct.springAssignmentProj.translation;

import com.ct.springAssignmentProj.appuser.AppUser;
import com.ct.springAssignmentProj.appuser.AppUserRepository;
import com.ct.springAssignmentProj.appuser.AppUserRole;
import com.ct.springAssignmentProj.appuser.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class TranslationServiceTest {
    @Mock private TranslationRepository translationRepository;
    private TranslationService underTest;

    @BeforeEach()
    void setUp(){
        underTest = new TranslationService(translationRepository);
    }

    @Test
    void canAddTranslation() {
        //given
        Translation translation = new Translation(
                "Pozdravljen svet",
                "SL"
        );

        //when
        underTest.addTranslation(translation);

        //then
        ArgumentCaptor<Translation> translationArgumentCaptor = ArgumentCaptor.forClass(Translation.class);
        verify(translationRepository).save(translationArgumentCaptor.capture());

        Translation capturedTranslation = translationArgumentCaptor.getValue();
        assertThat(capturedTranslation).isEqualTo(translation);
    }

    @Test
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