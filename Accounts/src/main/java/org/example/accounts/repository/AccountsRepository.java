package org.example.accounts.repository;

import org.example.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByEmail(String email);

    boolean existsByEmail(String email);
}