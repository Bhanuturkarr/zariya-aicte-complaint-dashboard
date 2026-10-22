package org.zariya.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SecurityConfig {
    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Local prototype permits the API; production must replace this with JWT authentication and role rules.
        return http.csrf(csrf->csrf.disable()).cors(Customizer.withDefaults()).authorizeHttpRequests(auth->auth.requestMatchers("/api/**","/swagger-ui/**","/v3/api-docs/**").permitAll().anyRequest().permitAll()).httpBasic(Customizer.withDefaults()).build();
    }
    @Bean CorsConfigurationSource corsConfigurationSource(@Value("${ZARIYA_CORS_ORIGIN:http://localhost:5173}") String origin){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of(origin,"http://127.0.0.1:5173"));c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Content-Type","Authorization"));UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();source.registerCorsConfiguration("/**",c);return source;}
}
