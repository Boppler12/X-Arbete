package org.example.accounts.Buyer;


import jakarta.persistence.*;
import lombok.*;
import org.example.accounts.entity.Accounts;

import java.time.LocalDateTime;

@Entity
@Table(name = "buyer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Accounts account;

    @Column(nullable = false, length = 150)
    private String companyName;

    @Column(length = 30)
    private String phoneNumber;

    @Column(length = 255)
    private String address;

    @Column(length = 100)
    private String organizationNumber;

    @Column(length = 255)
    private String websiteUrl;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}