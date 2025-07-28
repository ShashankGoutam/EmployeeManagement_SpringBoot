package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private Environment environment;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/login", "/public/**").permitAll()
                .anyRequest().authenticated()
            );

        
        if (!isTestEnvironment()) {
            http.oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/secure", true));
        }

        http.logout(logout -> logout
                .logoutSuccessUrl("/home")
                .permitAll());

        return http.build();
    }

    private boolean isTestEnvironment() {
        for (String profile : environment.getActiveProfiles()) {
            if (profile.equalsIgnoreCase("test")) return true;
        }
        return false;
    }
}
