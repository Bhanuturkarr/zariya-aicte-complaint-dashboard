package org.zariya.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Local prototype permits the API; production must replace this with JWT authentication and role rules.
        return http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/api/**","/swagger-ui/**","/v3/api-docs/**").permitAll().anyRequest().permitAll()).httpBasic(Customizer.withDefaults()).build();
    }
}
