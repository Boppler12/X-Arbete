package org.example.accounts.controller;

import lombok.RequiredArgsConstructor;
import org.example.accounts.DTO.InternalAccountResponse;
import org.example.accounts.entity.Accounts;
import org.example.accounts.repository.AccountsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/accounts")
@RequiredArgsConstructor
public class InternalAccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/by-email")
    public ResponseEntity<InternalAccountResponse> getAccountByEmail(@RequestParam String email) {
        return accountsRepository.findByEmail(email)
                .map(account -> ResponseEntity.ok(
                        InternalAccountResponse.builder()
                                .email(account.getEmail())
                                .passwordHash(account.getPasswordHash())
                                .build()
                ))
                .orElse(ResponseEntity.notFound().build());
    }
}
