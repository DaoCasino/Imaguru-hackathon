package com.dataart.maltahackaton.repository;

import com.dataart.maltahackaton.domain.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {
}
