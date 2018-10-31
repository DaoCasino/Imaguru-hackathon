package com.dataart.maltahackaton.controller;

import com.dataart.maltahackaton.domain.dto.LotteryDto;
import com.dataart.maltahackaton.service.LotteryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final LotteryService lotteryService;

    public AdminController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("createLottery")
    @ResponseBody
    public LotteryDto createLottery(@RequestBody LotteryDto lotteryDto) {
        return lotteryService.createLottery(lotteryDto);
    }
}
