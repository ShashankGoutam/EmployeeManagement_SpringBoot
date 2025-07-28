package com.example.demo.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecurityConfigTest {

    private SecurityConfig securityConfig;
    private MockEnvironment environment;

    @BeforeEach
    void setup() {
        environment = new MockEnvironment();
        securityConfig = new SecurityConfig();
        securityConfig.setEnvironment(environment);
    }

    @Test
    void testIsTestEnvironment_whenTestProfileActive_returnsTrue() {
        environment.setActiveProfiles("test");
        assertTrue(securityConfig.isTestEnvironment());
    }

    @Test
    void testIsTestEnvironment_whenNonTestProfileActive_returnsFalse() {
        environment.setActiveProfiles("prod");
        assertFalse(securityConfig.isTestEnvironment());
    }

    @Test
    void testFilterChain_doesNotThrowWithMockedHttpSecurity() throws Exception {
        environment.setActiveProfiles("test"); // test profile
        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);

        
        assertNotNull(securityConfig.filterChain(http));
    }
}
