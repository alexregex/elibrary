package com.libproject.elibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String index(Model model) {
        return "redirect:/books/all";
    }
}
