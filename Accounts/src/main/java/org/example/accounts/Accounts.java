package org.example.accounts;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private AccountRoles role;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private AccountStatus status;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String companyName;
}