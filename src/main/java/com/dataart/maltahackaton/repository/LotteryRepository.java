package com.dataart.maltahackaton.repository;

import com.dataart.maltahackaton.domain.Lottery;
import com.dataart.maltahackaton.domain.LotteryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    List<Lottery> findAllByStatus(LotteryStatus status);
}
