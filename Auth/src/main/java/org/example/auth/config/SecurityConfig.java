package org.example.auth.config;


import org.example.CommonSecurity.SharedSecurityConfig.ForbiddenHandler;
import org.example.CommonSecurity.SharedSecurityConfig.UnAuthorizedError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// ... existing code ...

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            UnAuthorizedError unAuthorizedError,
            ForbiddenHandler forbiddenHandler
    ) throws Exception {
        //inaktivering av CSRF då jag använder JWT
        http.csrf(crsf -> crsf.disable())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unAuthorizedError)
                        .accessDeniedHandler(forbiddenHandler)
                )
                // 2. Hantera behörigheter
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll() // Tillåt inloggning utan token
                        .anyRequest().authenticated()               // Kräv token för allt annat
                );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}