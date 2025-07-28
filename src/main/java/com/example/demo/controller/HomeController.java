package com.example.demo.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to the public home page!";
    }

    @GetMapping("/secure")
    public String secure(OAuth2AuthenticationToken token) {
        return "Hello " + token.getPrincipal().getAttribute("name") + "! You are logged in.";
    }
}
