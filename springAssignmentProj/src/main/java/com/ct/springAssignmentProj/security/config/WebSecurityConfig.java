package com.ct.springAssignmentProj.security.config;

import com.ct.springAssignmentProj.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/hello-rest").permitAll()
                .requestMatchers("/hello").permitAll()
                .requestMatchers("/registration").permitAll()
                .requestMatchers("/secure/hello").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/secure/add").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return http.build();
    }
}
