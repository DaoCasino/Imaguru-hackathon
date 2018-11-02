package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.domain.dto.LotteryCreateRequest;
import com.dataart.maltahackaton.domain.dto.LotteryResponse;
import com.dataart.maltahackaton.service.LotteryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final LotteryService lotteryService;

    public AdminController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("admin/createLottery")
    public String createLotteryPage(Model model) {
        return "lotteryCreate";
    }

    @PostMapping(value = "admin/createLottery", consumes = "multipart/form-data")
    @ResponseBody
    public LotteryResponse createLottery(@ModelAttribute LotteryCreateRequest lottery) throws Exception {
        return lotteryService.createLottery(lottery);
    }
}
