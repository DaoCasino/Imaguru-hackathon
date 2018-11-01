package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.blockchain.BlockchainConfig;
import com.dataart.maltahackaton.blockchain.LotteryProvider;
import com.dataart.maltahackaton.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Slf4j
@Controller
@RequestMapping("lotteries")
public class LotteryController {

    private final LotteryProvider lotteryProvider;
    private final LotteryService lotteryService;
    private final BlockchainConfig blockchainConfig;

    public LotteryController(LotteryProvider lotteryProvider, LotteryService lotteryService, BlockchainConfig blockchainConfig) {
        this.lotteryProvider = lotteryProvider;
        this.lotteryService = lotteryService;
        this.blockchainConfig = blockchainConfig;
    }

    @GetMapping("getAll")
    public String getAll(Model model) throws Exception {
        log.info("Start deploying contract...........");
        String contractAddress = lotteryProvider.deploy(blockchainConfig.getOwnerWalletPrivateKey(), "0x6dfE9E7B55EbEB7D5d494503b5b8C91B95B925Fa", BigInteger.TEN,
                BigInteger.TEN, new BigInteger("100000000000000000"), new BigInteger("100000000000000000"), BigInteger.TEN, new BigInteger("90"));
        log.info("__________________________" + contractAddress);

        model.addAttribute("lotteries", lotteryService.getAll());
        return "lotteries";
    }

    @GetMapping("getOne")
    public String getLotteryById(Model model, @RequestParam Long id) {
        model.addAttribute("lottery", lotteryService.getLotteryById(id));
        return "lottery";
    }

}
