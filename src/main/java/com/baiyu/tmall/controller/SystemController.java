package com.baiyu.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/unauth")
    public String unauth() {
        return "system/unauth";
    }
}
