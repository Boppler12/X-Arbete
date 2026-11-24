package org.example.auth;

import org.example.CommonSecurity.SharedSecurityConfig.ForbiddenHandler;
import org.example.CommonSecurity.SharedSecurityConfig.UnAuthorizedError;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        UnAuthorizedError.class,
        ForbiddenHandler.class
})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}