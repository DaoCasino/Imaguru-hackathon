package com.dataart.maltahackaton.repository;

import com.dataart.maltahackaton.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
