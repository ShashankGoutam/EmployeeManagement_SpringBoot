package com.example.demo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return registrationId -> null; // Dummy for tests
    }
}
