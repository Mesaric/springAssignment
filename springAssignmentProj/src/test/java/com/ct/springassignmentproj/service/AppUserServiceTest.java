package com.ct.springassignmentproj.service;

import com.ct.springassignmentproj.model.AppUser;
import com.ct.springassignmentproj.model.AppUserRole;
import com.ct.springassignmentproj.repository.AppUserRepository;
import com.ct.springassignmentproj.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock private AppUserRepository appUserRepository;
    @Mock private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AppUserService underTest;

    @BeforeEach()
    void setUp(){
        underTest = new AppUserService(appUserRepository, bCryptPasswordEncoder);
    }

    @Test
    void canSignUpUser() {
        //given
        AppUser appUser = new AppUser(
                "janez.velikonja@gmail.com",
                "password",
                AppUserRole.USER
        );
        //when
        underTest.signUpUser(appUser);

        //then
        ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);
        verify(appUserRepository).save(appUserArgumentCaptor.capture());

        AppUser capturedAppUser =  appUserArgumentCaptor.getValue();
        assertThat(capturedAppUser).isEqualTo(appUser);
    }

    @Test
    void willThrowWhenUserEmailIsTaken() {
        //given
        String USER_EXISTS_MSG = "User with email %s already exists";
        String email = "janez.velikonja@gmail.com";
        AppUser appUser = new AppUser(
                email,
                "password",
                AppUserRole.USER
        );
        given((appUserRepository.findByEmail(email))).willReturn(Optional.of(appUser));

        //then
        assertThatThrownBy(() -> underTest.signUpUser(appUser))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format(USER_EXISTS_MSG, appUser.getEmail()));

        verify(appUserRepository, never()).save(any());
    }

}