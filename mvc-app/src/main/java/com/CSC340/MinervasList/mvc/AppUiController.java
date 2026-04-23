package com.CSC340.MinervasList.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUiController {

    @GetMapping("/")
    public String home() {
        return "redirect:/seller/listings";
    }
}
