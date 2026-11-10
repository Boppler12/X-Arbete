package org.example.auth;

public record AuthResponse(String token, long expiresIn) {
}
