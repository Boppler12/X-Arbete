package org.example.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //inaktivering av CSRF då jag använder JWT
        http.csrf(crsf -> crsf.disable())
                // 2. Hantera behörigheter
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll() // Tillåt inloggning utan token
                        .anyRequest().authenticated()               // Kräv token för allt annat
                );

        return http.build();
    }
}
