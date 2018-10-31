package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.service.LotteryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lotteries")
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("lotteries", lotteryService.getAll());
        return "lottery";
    }
}
