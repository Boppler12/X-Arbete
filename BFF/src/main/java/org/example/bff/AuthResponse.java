package org.example.bff;

public record AuthResponse(String token, long expiresIn) {
}
