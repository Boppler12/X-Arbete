package org.example.auth.DTO;

public record AuthResponse(String token, long expiresIn) {
}
