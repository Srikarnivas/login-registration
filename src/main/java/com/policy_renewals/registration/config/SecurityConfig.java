package com.policy_renewals.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/agents/register", "/api/agents/login").permitAll()  // Allow access to agent-related endpoints
                                .requestMatchers("/api/policies/**", "/api/policies-brought/**").permitAll()  // Allow access to policy-related endpoints
                                .anyRequest().authenticated()  // All other endpoints require authentication
                );
        return http.build();
    }
}
