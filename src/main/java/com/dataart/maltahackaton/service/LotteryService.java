package com.dataart.maltahackaton.service;

import com.dataart.maltahackaton.blockchain.BlockchainCharity;
import com.dataart.maltahackaton.blockchain.BlockchainConfig;
import com.dataart.maltahackaton.blockchain.LotteryProvider;
import com.dataart.maltahackaton.domain.Lottery;
import com.dataart.maltahackaton.domain.LotteryStatus;
import com.dataart.maltahackaton.domain.dto.LotteryCreateRequest;
import com.dataart.maltahackaton.domain.dto.LotteryResponse;
import com.dataart.maltahackaton.exception.MhException;
import com.dataart.maltahackaton.repository.LotteryRepository;
import com.dataart.maltahackaton.utils.BlockchainUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class LotteryService {

    @Value("${lottery.fee.rate}")
    private Long feeRate;
    @Value("${lottery.fee.max}")
    private BigDecimal maxFee;

    private final LotteryRepository lotteryRepository;
    private final ModelMapper modelMapper;
    private final LotteryProvider lotteryProvider;
    private final BlockchainConfig blockchainConfig;

    public LotteryService(LotteryRepository lotteryRepository, ModelMapper modelMapper, LotteryProvider lotteryProvider, BlockchainConfig blockchainConfig) {
        this.lotteryRepository = lotteryRepository;
        this.modelMapper = modelMapper;
        this.lotteryProvider = lotteryProvider;
        this.blockchainConfig = blockchainConfig;
    }

    public List<LotteryResponse> findAllActive() {
        List<Lottery> lotteries = lotteryRepository.findAllByStatus(LotteryStatus.ACTIVE);
        return mapToResponseObjects(lotteries);
    }

    public List<LotteryResponse> findAllInactive() {
        List<Lottery> lotteries = lotteryRepository.findAllByStatus(LotteryStatus.INACTIVE);
        return mapToResponseObjects(lotteries);
    }

    public List<LotteryResponse> findByUserWallet(String walletAddress) {
        List<Lottery> allLotteries = lotteryRepository.findAll();
        List<Lottery> userLotteries = new ArrayList<>();
        for (Lottery lottery : allLotteries) {
            BlockchainCharity blockchainCharity = lotteryProvider.loadFromOwner(lottery.getContractAddress(),
                    blockchainConfig.getGasPrice(), blockchainConfig.getGasLimit());
            try {
                if (blockchainCharity.holderTickets(walletAddress, BigInteger.ZERO).send().getValue1().compareTo(BigInteger.ZERO) > 0) {
                    userLotteries.add(lottery);
                }
            } catch (Exception e) {
                log.error("Error while searching user lotteries: ", e);
            }
        }
        return mapToResponseObjects(userLotteries);
    }

    private List<LotteryResponse> mapToResponseObjects(List<Lottery> lotteries) {
        List<LotteryResponse> lotteryResponses = new ArrayList<>();
        lotteries.forEach(lottery -> lotteryResponses.add(mapLotteryWithAdditionalData(lottery)));
        return lotteryResponses;
    }

    public LotteryResponse createLottery(LotteryCreateRequest createRequest) throws Exception {
        String contractAddress = lotteryProvider.deploy(createRequest.getFundAddress(),
                BigInteger.valueOf(createRequest.getDuration()),
                BigInteger.valueOf(feeRate),
                BlockchainUtils.convertToBlockchainUnits(maxFee),
                BlockchainUtils.convertToBlockchainUnits(createRequest.getTicketPrice()),
                BigInteger.valueOf(createRequest.getPrizePoolRate()));

        Lottery lottery = modelMapper.map(createRequest, Lottery.class);
        lottery.setStatus(LotteryStatus.ACTIVE);
        lottery.setStartDate(LocalDateTime.now());
        lottery.setEndDate(lottery.getStartDate().plusMinutes(createRequest.getDuration()));
        lottery.setContractAddress(contractAddress);
        lottery.setLotteryPicture(createRequest.getImages().get(0).getBytes());
        lottery.setFundPicture(createRequest.getImages().get(1).getBytes());
        lotteryRepository.save(lottery);

        return modelMapper.map(lottery, LotteryResponse.class);
    }

    public LotteryResponse getLotteryById(Long id) {
        Lottery lottery = lotteryRepository.findById(id).orElseThrow(() -> new MhException("Lottery not found"));
        return mapLotteryWithAdditionalData(lottery);
    }

    private LotteryResponse mapLotteryWithAdditionalData(Lottery lottery) {
        LotteryResponse response = modelMapper.map(lottery, LotteryResponse.class);
        response.setTimeRemaining(calculateTimeRemaining(lottery.getEndDate()));
        BlockchainCharity blockchainCharity = lotteryProvider.loadReadOnly(lottery.getContractAddress());
        BigDecimal donation = null;
        Long ticketsCount = null;
        try {
            ticketsCount = blockchainCharity.currentTicketNumber().send().longValue() + 1L;
            donation = lottery.getTicketPrice().multiply(BigDecimal.valueOf(ticketsCount));
            if (LotteryStatus.INACTIVE == lottery.getStatus()) {
                String winnerAddress = blockchainCharity.allTickets(blockchainCharity.winnerTicketNumber().send()).send().getValue2();
                response.setWinnerAddress(winnerAddress);
            }
        } catch (Exception e) {
            log.error("Error while getting currentTicketNumber: {}", e);
        }
        response.setDonation(donation);
        BigDecimal prizePool = calculateFee(response.getDonation(), lottery.getPrizePoolRate(), BigDecimal.ZERO);
        response.setPrizePool(prizePool);
        BigDecimal profit = calculateFee(donation, feeRate, maxFee);
        response.setPayout(donation.subtract(prizePool).subtract(profit));
        response.setTicketCount(ticketsCount);
        response.setLotteryPicture(Base64.getEncoder().encodeToString(lottery.getLotteryPicture()));
        response.setFundPicture(Base64.getEncoder().encodeToString(lottery.getFundPicture()));
        return response;
    }

    private BigDecimal calculateFee(BigDecimal amount, Long feeRate, BigDecimal maxFee) {
        BigDecimal fee = BigDecimal.ZERO;
        if (feeRate != 0) {
            fee = BigDecimal.valueOf(feeRate).multiply(amount).divide(new BigDecimal("100"), RoundingMode.HALF_DOWN);
            if (maxFee.compareTo(BigDecimal.ZERO) != 0 && fee.compareTo(maxFee) > 0) {
                return maxFee;
            }
        }
        return fee;
    }

    private String calculateTimeRemaining(LocalDateTime endDate) {
        if (endDate.isBefore(LocalDateTime.now())) {
            return "0";
        }

        Duration duration = Duration.between(LocalDateTime.now(), endDate);

        long seconds = Math.abs(duration.getSeconds());
        long hours = seconds / 3600;
        seconds -= (hours * 3600);
        long minutes = seconds / 60;
        seconds -= (minutes * 60);

        return hours + "h " + minutes + "m " + seconds + "s";
    }
}
