package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

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
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/home")
                .permitAll()
            );

        http
            .exceptionHandling(ex -> ex
                .defaultAuthenticationEntryPointFor(
                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                    request -> request.getRequestURI().startsWith("/api/")
                )
            );

        if (!isTestEnvironment()) {
            http.oauth2Login(oauth2 -> oauth2
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
