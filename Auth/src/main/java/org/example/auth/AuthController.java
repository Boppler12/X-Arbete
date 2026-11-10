package org.example.auth;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWS_Token jwsToken;

    public AuthController(JWS_Token jwsToken) {
        this.jwsToken = jwsToken;
    }

    // POST /auth/login - kontrollerar uppgifterna och returnerar en JWT
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // TODO: byt mot riktig kontroll mot databasen (användare + hashat lösenord)
        if ("admin".equals(loginRequest.username()) && "password".equals(loginRequest.password())) {
            String token = jwsToken.generateToken(loginRequest.username());
            return ResponseEntity.ok(new AuthResponse(token, 3600));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
