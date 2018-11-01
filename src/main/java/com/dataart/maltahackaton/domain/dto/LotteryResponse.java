package com.dataart.maltahackaton.domain.dto;

import com.dataart.maltahackaton.domain.LotteryStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.format.annotation.DateTimeFormat.ISO.TIME;

@Data
public class LotteryResponse {

    private Long id;
    private String name;
    private String contractAddress;
    private String fundName;
    private String fundDescription;
    private LotteryStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private BigDecimal ticketPrice;
    private Long ticketCount;
    private BigDecimal donation;
    private BigDecimal prizePool;
    private BigDecimal payout;
    private String timeRemaining;
    private String lotteryPicture;
    private String fundPicture;

}
