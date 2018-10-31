package com.dataart.maltahackaton.domain.dto;

import com.dataart.maltahackaton.domain.LotteryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LotteryDto {

    private Long id;
    private String name;
    private String contractAddress;
    private String fundName;
    private String fundDescription;
    private LotteryStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
