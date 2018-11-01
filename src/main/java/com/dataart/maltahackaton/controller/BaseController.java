package com.dataart.maltahackaton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("games")
    public String getGamesPage() {
        return "games";
    }
}
