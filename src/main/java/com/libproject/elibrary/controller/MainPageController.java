package com.libproject.elibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class MainPageController {

    @RequestMapping
    public String mainPage() {
        return "redirect:/books/all";
    }
}
