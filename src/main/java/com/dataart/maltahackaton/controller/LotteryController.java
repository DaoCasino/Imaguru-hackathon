package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.blockchain.BlockchainCharity;
import com.dataart.maltahackaton.blockchain.BlockchainConfig;
import com.dataart.maltahackaton.blockchain.LotteryProvider;
import com.dataart.maltahackaton.domain.dto.LotteryResponse;
import com.dataart.maltahackaton.service.LotteryService;
import com.dataart.maltahackaton.utils.BlockchainUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
@Controller
@RequestMapping("lotteries")
public class LotteryController {

    private final LotteryProvider lotteryProvider;
    private final LotteryService lotteryService;
    private final BlockchainConfig blockchainConfig;
    private final Web3j web3j;

    public LotteryController(LotteryProvider lotteryProvider, LotteryService lotteryService, BlockchainConfig blockchainConfig, Web3j web3j) {
        this.lotteryProvider = lotteryProvider;
        this.lotteryService = lotteryService;
        this.blockchainConfig = blockchainConfig;
        this.web3j = web3j;
    }

    @ResponseBody
    @GetMapping("deploy")
    public String deploy() throws Exception {
        return lotteryProvider.deploy("0x6dfE9E7B55EbEB7D5d494503b5b8C91B95B925Fa", new BigInteger("2"),
                BigInteger.TEN, new BigInteger("100000000000000000"), new BigInteger("10000000000000000"), BigInteger.TEN);
    }

    @ResponseBody
    @GetMapping("sendFunds/{contractAddress}")
    public void sendFunds(@PathVariable String contractAddress) throws Exception {
        Transfer.sendFunds(
                web3j,
                BlockchainUtils.buildCredentials("79758460756326615189336916476751864146397195680622050055491221201098956741270"),
                contractAddress,
                new BigDecimal("10000000000000000"), Convert.Unit.WEI).send();
        log.info("Funds sent");
    }

    @ResponseBody
    @GetMapping("finish/{contractAddress}")
    public void finish(@PathVariable String contractAddress) throws Exception {
        BlockchainCharity contract = lotteryProvider.loadFromOwner(contractAddress);
        contract.finishLottery().send();
        log.info("lottery finished");
    }

    @ResponseBody
    @GetMapping("withdrawal/{contractAddress}")
    public void withdrawal(@PathVariable String contractAddress) throws Exception {
        BlockchainCharity contract = lotteryProvider.loadFromOwner(contractAddress);
        contract.withdrawOwnersAmount().send();
        log.info("Owner is rich now");
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
