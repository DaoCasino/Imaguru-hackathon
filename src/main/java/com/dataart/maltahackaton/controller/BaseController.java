package com.dataart.maltahackaton.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/lotteries/getAll";
    }

    @GetMapping("games")
    public String getGamesPage() {
        return "games";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
