package org.example.auth;

import jakarta.validation.constraints.NotBlank;

//Används när en användare skickar in sina uppgifter
public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
