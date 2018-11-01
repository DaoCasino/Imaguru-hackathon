package com.dataart.maltahackaton.service;

import com.dataart.maltahackaton.domain.Lottery;
import com.dataart.maltahackaton.domain.LotteryStatus;
import com.dataart.maltahackaton.domain.dto.LotteryCreateRequest;
import com.dataart.maltahackaton.domain.dto.LotteryResponse;
import com.dataart.maltahackaton.exception.MhException;
import com.dataart.maltahackaton.repository.LotteryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LotteryService {

    @Value("${lottery.fee.rate}")
    private BigDecimal feeRate;
    @Value("${lottery.fee.max}")
    private BigDecimal maxFee;

    private final LotteryRepository lotteryRepository;
    private final ModelMapper modelMapper;

    public LotteryService(LotteryRepository lotteryRepository, ModelMapper modelMapper) {
        this.lotteryRepository = lotteryRepository;
        this.modelMapper = modelMapper;
    }

    public List<LotteryResponse> getAll() {
        return modelMapper.map(lotteryRepository.findAll(), new TypeToken<List<LotteryResponse>>() {
        }.getType());
    }

    public LotteryResponse createLottery(LotteryCreateRequest createRequest) {
        Lottery lottery = modelMapper.map(createRequest, Lottery.class);
        lottery.setStatus(LotteryStatus.ACTIVE);
        lottery.setStartDate(LocalDateTime.now());
        lottery.setEndDate(lottery.getStartDate().plusMinutes(createRequest.getDuration()));
        lottery.setContractAddress("");
        lotteryRepository.save(lottery);
        return modelMapper.map(lottery, LotteryResponse.class);
    }

    public LotteryResponse getLotteryById(Long id) {
        Lottery lottery = lotteryRepository.findById(id).orElseThrow(() -> new MhException("Lottery not found"));
        LotteryResponse response = modelMapper.map(lottery, LotteryResponse.class);
        response.setTimeRemaining(calculateTimeRemaining(lottery.getEndDate()));
        response.setDonation(lottery.getTicketPrice().multiply(BigDecimal.valueOf(lottery.getTicketCount())));
        response.setPrizePool(calculateFee(response.getDonation(), lottery.getPrizePoolRate(), BigDecimal.ZERO));
        return response;
    }

    private BigDecimal calculateFee(BigDecimal amount, BigDecimal feeRate, BigDecimal maxFee) {
        BigDecimal fee = BigDecimal.ZERO;
        if (feeRate.compareTo(BigDecimal.ZERO) != 0) {
            fee = feeRate.multiply(amount).divide(new BigDecimal("100"), RoundingMode.HALF_DOWN);
            if (maxFee.compareTo(BigDecimal.ZERO) != 0 && fee.compareTo(maxFee) > 0) {
                return maxFee;
            }
        }
        return fee;
    }

    private String calculateTimeRemaining(LocalDateTime endDate) {
        Duration duration = Duration.between(LocalDateTime.now(), endDate);

        long seconds = Math.abs(duration.getSeconds());
        long hours = seconds / 3600;
        seconds -= (hours * 3600);
        long minutes = seconds / 60;
        seconds -= (minutes * 60);

        return hours + "h " + minutes + "m " + seconds + "s";
    }
}
