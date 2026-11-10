package org.example.auth.Controller;

import jakarta.validation.Valid;
import org.example.auth.DTO.AuthResponse;
import org.example.auth.DTO.LoginRequest;
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
    private final RestClient restClient;

    public AuthController(JWS_Token jws_token) {
        this.jws_token = jws_token;
        this.restClient = RestClient.builder().build();
    }

    @PostMapping("/login")
      public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        //Validering för användarnamn/lösenord inte är tomma

        if ("admin".equals(loginRequest.username()) && "password".equals(loginRequest.password())) {
            // Skapa admin-användaren i User-tjänsten om den inte finns
            restClient.post()
                    .uri("http://localhost:8083/users")
                    .body(new UserCreationRequest("admin", "Administrator", "admin@example.com"))
                    .retrieve()
                    .toBodilessEntity();

            String token = jws_token.generateToken(loginRequest.username());
            return ResponseEntity.ok(new AuthResponse(token, 3600));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    private record UserCreationRequest(String userId, String name, String email) {}
}

