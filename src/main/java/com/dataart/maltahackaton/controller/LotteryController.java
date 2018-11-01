package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.blockchain.BlockchainConfig;
import com.dataart.maltahackaton.blockchain.LotteryProvider;
import com.dataart.maltahackaton.domain.dto.LotteryResponse;
import com.dataart.maltahackaton.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAll(Model model) {
        model.addAttribute("activeLoteries", lotteryService.findAllActive());
        model.addAttribute("inactiveLoteries", lotteryService.findAllInactive());
        return "lotteries";
    }

    @GetMapping("getOne")
    public String getLotteryById(Model model, @RequestParam Long id) {
        model.addAttribute("lottery", lotteryService.getLotteryById(id));
        return "lottery";
    }

    @GetMapping("{id}")
    @ResponseBody
    public LotteryResponse getRestLotteryById(@PathVariable Long id) {
        return lotteryService.getLotteryById(id);
    }

}
