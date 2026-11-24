package org.example.accounts.config;

import org.example.CommonSecurity.SharedSecurityConfig.ForbiddenHandler;
import org.example.CommonSecurity.SharedSecurityConfig.UnAuthorizedError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            UnAuthorizedError unAuthorizedError,
            ForbiddenHandler forbiddenHandler
    ) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unAuthorizedError)
                        .accessDeniedHandler(forbiddenHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/buyers/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/internal/accounts/by-email").hasRole("INTERNAL_SERVICE")
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}