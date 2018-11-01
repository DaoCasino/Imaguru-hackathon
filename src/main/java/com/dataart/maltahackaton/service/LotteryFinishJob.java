package com.dataart.maltahackaton.service;

import com.dataart.maltahackaton.blockchain.BlockchainCharity;
import com.dataart.maltahackaton.blockchain.BlockchainConfig;
import com.dataart.maltahackaton.blockchain.LotteryProvider;
import com.dataart.maltahackaton.domain.Lottery;
import com.dataart.maltahackaton.domain.LotteryStatus;
import com.dataart.maltahackaton.repository.LotteryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class LotteryFinishJob {

    private final LotteryRepository lotteryRepository;
    private final LotteryProvider lotteryProvider;
    private final BlockchainConfig blockchainConfig;

    public LotteryFinishJob(LotteryRepository lotteryRepository, LotteryProvider lotteryProvider, BlockchainConfig blockchainConfig) {
        this.lotteryRepository = lotteryRepository;
        this.lotteryProvider = lotteryProvider;
        this.blockchainConfig = blockchainConfig;
    }

    @Scheduled(cron = "${lottery-finish.job.cron}")
    public void executeJob() {
        log.info("Starting lotteries sync...");
        List<Lottery> potentiallyFinishedLotteries =
                lotteryRepository.findAllByStatusAndEndDateBeforeOrCompletedAndEndDateBefore(LotteryStatus.ACTIVE,
                        LocalDateTime.now(), false, LocalDateTime.now());
        log.info("Found {} active lotteries", potentiallyFinishedLotteries.size());
        log.info("Starting finish lotteries...");

        for (Lottery lottery : potentiallyFinishedLotteries) {
            BlockchainCharity blockchainCharity = lotteryProvider.loadFromOwner(lottery.getContractAddress(),
                    blockchainConfig.getGasPrice(), blockchainConfig.getGasLimit());
            try {
                if (blockchainCharity.lotteryClosed().send()) {
                    log.info("Withdrawing funds from closed lottery to wallet: {}", blockchainConfig.getOwnerWalletAddress());
                    blockchainCharity.withdrawOwnersAmount().send();
                    lottery.setCompleted(true);
                    lotteryRepository.save(lottery);
                    log.info("Withdrawing completed...");
                } else {
                    log.info("Finishing lottery {}", lottery.getContractAddress());
                    blockchainCharity.finishLottery().send();
                    lottery.setStatus(LotteryStatus.INACTIVE);
                    lotteryRepository.save(lottery);
                    log.info("Lottery finished...");
                }
            } catch (Exception e) {
                log.error("Error while finishing lottery", e);
            }
        }
        log.info("Finishing of lotteries is completed...");
        log.info("Lotteries sync finished...");
    }
}
