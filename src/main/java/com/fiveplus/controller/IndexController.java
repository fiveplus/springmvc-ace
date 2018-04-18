package com.fiveplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/site")
public class IndexController {

    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model){
        return "main";
    }
}
