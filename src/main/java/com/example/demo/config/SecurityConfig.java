package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/public/**").permitAll()
                .anyRequest().authenticated()
            );

        try {
            
            http.oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/secure", true)
            );
        } catch (Exception ignored) {
           
        }

        http.logout(logout -> logout
            .logoutSuccessUrl("/home")
            .permitAll()
        );

        return http.build();
    }
}
