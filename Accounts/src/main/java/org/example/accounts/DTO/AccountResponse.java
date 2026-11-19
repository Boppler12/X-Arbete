package org.example.accounts.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.accounts.enums.AccountRoles;
import org.example.accounts.enums.AccountStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AccountResponse {

    private Long id;

    private String email;

    private AccountRoles role;

    private AccountStatus status;

    private String firstName;
    private String lastName;

    private String phoneNumber;

    private String address;

    private String companyName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}