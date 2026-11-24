package org.example.accounts.Buyer;

import lombok.RequiredArgsConstructor;
import org.example.accounts.DTO.AccountResponse;
import org.example.accounts.DTO.CreateBuyerRequest;
import org.example.accounts.entity.Accounts;
import org.example.accounts.enums.AccountRoles;
import org.example.accounts.enums.AccountStatus;
import org.example.accounts.repository.AccountsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyerService {

    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountResponse createBuyer(CreateBuyerRequest request) {
        if (accountsRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (request.getCompanyName() != null && request.getCompanyName().length() > 150) {
            throw new IllegalArgumentException("Company name must be 150 characters or fewer");
        }

        Accounts buyer = Accounts.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(AccountRoles.BUYERS)
                .status(AccountStatus.ACTIVE)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .companyName(request.getCompanyName())
                .build();

        Accounts savedBuyer = accountsRepository.save(buyer);

        return mapToResponse(savedBuyer);
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