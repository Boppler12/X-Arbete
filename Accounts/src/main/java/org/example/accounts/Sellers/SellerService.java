package org.example.accounts.Sellers;

import lombok.RequiredArgsConstructor;
import org.example.accounts.DTO.AccountResponse;
import org.example.accounts.DTO.CreateSellerRequest;
import org.example.accounts.entity.Accounts;
import org.example.accounts.enums.AccountRoles;
import org.example.accounts.enums.AccountStatus;
import org.example.accounts.repository.AccountsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.ExceptionHandler.GlobalExceptionHandler;


@Service
@RequiredArgsConstructor
public class SellerService {

    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountResponse createSeller(CreateSellerRequest request) {
        if (accountsRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Accounts seller = Accounts.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(AccountRoles.SELLERS)
                .status(AccountStatus.ACTIVE)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .companyName(request.getCompanyName())
                .build();

        Accounts savedSeller = accountsRepository.save(seller);

        return mapToResponse(savedSeller);
    }

    private AccountResponse mapToResponse(Accounts account) {
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .role(account.getRole())
                .status(account.getStatus())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .phoneNumber(account.getPhoneNumber())
                .address(account.getAddress())
                .companyName(account.getCompanyName())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}