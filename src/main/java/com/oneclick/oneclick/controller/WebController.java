package com.oneclick.oneclick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class WebController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "이미지 뷰");
        model.addAttribute("img", "image/test.png");
        return "hello";
    }
    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }
}
