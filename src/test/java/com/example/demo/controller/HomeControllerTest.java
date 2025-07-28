package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    @Test
    void testHome() {
        HomeController controller = new HomeController();
        String result = controller.home();
        assertEquals("Welcome to the public home page!", result);
    }

    @Test
    void testSecure() {
        OAuth2AuthenticationToken token = mock(OAuth2AuthenticationToken.class);
        var principal = mock(org.springframework.security.oauth2.core.user.OAuth2User.class);

        when(token.getPrincipal()).thenReturn(principal);
        when(principal.getAttribute("name")).thenReturn("Shashank");

        HomeController controller = new HomeController();
        String result = controller.secure(token);
        assertEquals("Hello Shashank! You are logged in.", result);
    }
}
