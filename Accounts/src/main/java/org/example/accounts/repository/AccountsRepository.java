package org.example.accounts.repository;

import org.example.accounts.entity.Accounts;

import java.util.Optional;

public interface AccountsRepository extends JpaRepositry<Accounts,Long>{

    Optional<Accounts> findByEmail(String email);

    boolean existsByEmail(String email);
}
