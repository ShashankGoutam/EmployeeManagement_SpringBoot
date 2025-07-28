package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Autowired
    private Environment environment;

    void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/login", "/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/home")
                .permitAll()
            );

        if (!isTestEnvironment()) {
            http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/secure", true)
            );
        }

        if (isTestEnvironment()) {
            http.csrf(csrf -> csrf.disable());
        }

        return http.build();
    }

    boolean isTestEnvironment() {
        return Arrays.asList(environment.getActiveProfiles()).contains("test");
    }
}
