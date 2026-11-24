package org.example.auth.Controller;

import jakarta.validation.Valid;
import org.example.auth.DTO.AuthResponse;
import org.example.auth.DTO.LoginRequest;
import org.example.auth.Service.AuthService;
import org.example.auth.Service.JWS_Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWS_Token jws_token;
    private final AuthService authService;
    private final RestClient restClient;

    public AuthController(JWS_Token jws_token, AuthService authService) {
        this.jws_token = jws_token;
        this.authService = authService;
        this.restClient = RestClient.builder().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        boolean authenticated = authService.authenticate(
                loginRequest.username(),
                loginRequest.password()
        );

        if (!authenticated) {
            return ResponseEntity.status(401).build();
        }

        String token = jws_token.generateToken(loginRequest.username());

        return ResponseEntity.ok(new AuthResponse(token, 3600));
    }

    private record UserCreationRequest(String userId, String name, String email) {}
}

