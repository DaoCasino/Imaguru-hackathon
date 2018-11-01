package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.service.LotteryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("lotteries")
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("getAll")
    public String getAll(Model model) {
        model.addAttribute("lotteries", lotteryService.getAll());
        return "lotteries";
    }

    @GetMapping("getOne")
    public String getLotteryById(Model model, @RequestParam Long id) {
        model.addAttribute("lottery", lotteryService.getLotteryById(id));
        return "lottery";
    }

}
