package org.example.accounts;

import org.example.CommonSecurity.SharedSecurityConfig.ForbiddenHandler;
import org.example.CommonSecurity.SharedSecurityConfig.UnAuthorizedError;
import org.example.ExceptionHandler.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        GlobalExceptionHandler.class,
        UnAuthorizedError.class,
        ForbiddenHandler.class
})
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}