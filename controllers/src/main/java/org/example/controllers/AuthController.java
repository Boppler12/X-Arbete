package org.example.controllers;

import org.example.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RequestMapping
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final RestClient restClient;

    public AuthController(Jwtservice jwtservice)
        this.jwtService= jwtService;
        this.restClient= RestClient.builder().build();

    @PostMapping("/login")
    // Validering så att användarenamn och lösenord inte lämnas tomma
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest{

        if("admin".equals(loginRequest.username()&& "password".equals(loginRequest.password()))) {
            restClient.post()
                    .uri("http://localhost:8083/users/login")
                    .body(new UserCreationRequest("admin", "Administrator", "admin@example.com"))
                    .retrieve()
                    .toBodilessEntity();

            String token = jwtService.generateToken(loginRequest.username());
            return ResponseEntity.ok(new AuthResponse(token, 3600));
        }else{
            return ResponseEntity.status(401).build();
        }
    }

    private record UserCreationRequest(String username, String name, String email){}
}
