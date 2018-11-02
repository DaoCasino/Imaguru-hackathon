package com.dataart.maltahackaton.controller;

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

import java.util.List;

@Slf4j
@Controller
@RequestMapping("lotteries")
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

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
