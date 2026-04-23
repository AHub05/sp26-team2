package com.CSC340.MinervasList.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUiController {
    
    @GetMapping({"", "/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
