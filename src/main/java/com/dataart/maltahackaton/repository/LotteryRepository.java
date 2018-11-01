package com.dataart.maltahackaton.repository;

import com.dataart.maltahackaton.domain.Lottery;
import com.dataart.maltahackaton.domain.LotteryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    List<Lottery> findAllByStatusAndEndDateBeforeOrCompletedAndEndDateBefore(LotteryStatus status, LocalDateTime endDate, Boolean completed, LocalDateTime endDate2);

    List<Lottery> findAllByStatus(LotteryStatus status);

}
