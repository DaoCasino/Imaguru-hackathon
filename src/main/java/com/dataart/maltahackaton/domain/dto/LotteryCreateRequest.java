package com.dataart.maltahackaton.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LotteryCreateRequest {

    private String name;
    private String description;
    private Long duration;
    private BigDecimal ticketPrice;
    private BigDecimal prizePoolRate;
    private String fundAddress;
    private List<MultipartFile> images;

}
