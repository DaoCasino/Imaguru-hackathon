package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.blockchain.BlockchainCharity;
import com.dataart.maltahackaton.blockchain.BlockchainConfig;
import com.dataart.maltahackaton.blockchain.LotteryProvider;
import com.dataart.maltahackaton.domain.dto.LotteryResponse;
import com.dataart.maltahackaton.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;

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

    // TODO: Remove from this
    @ResponseBody
    @GetMapping("deploy")
    public String deploy() throws Exception {
        return lotteryProvider.deploy("0xfed7907edc850959e570092ac0c45726f48978dd", new BigInteger("3"),
                BigInteger.TEN, new BigInteger("100000000000000000"), new BigInteger("1000000000000000"), BigInteger.TEN);
    }

    @ResponseBody
    @GetMapping("finish/{contractAddress}")
    public void finish(@PathVariable String contractAddress) throws Exception {
        BlockchainCharity contract = lotteryProvider.loadFromOwner(contractAddress);
        TransactionReceipt transactionReceipt = contract.finishLottery().send();
        log.info(transactionReceipt.getBlockNumber().toString());
        log.info(transactionReceipt.getTransactionHash());
        log.info("lottery finished");
    }

    @ResponseBody
    @GetMapping("withdrawal/{contractAddress}")
    public void withdrawal(@PathVariable String contractAddress) throws Exception {
        BlockchainCharity contract = lotteryProvider.loadFromOwner(contractAddress);
        contract.withdrawOwnersAmount().send();
        log.info("Owner is rich now");
    }
    // TODO: Remove to this

    @GetMapping("getAll")
    public String getAll(Model model) {
        model.addAttribute("activeLotteries", lotteryService.findAllActive());
        model.addAttribute("inactiveLotteries", lotteryService.findAllInactive());
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

    @GetMapping("getAllByUserWallet")
    @ResponseBody
    public List<LotteryResponse> findAllByUserWallet(@RequestParam("address") String address) {
        return lotteryService.findByUserWallet(address);
    }

}
