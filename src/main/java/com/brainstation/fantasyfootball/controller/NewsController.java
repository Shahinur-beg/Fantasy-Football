package com.brainstation.fantasyfootball.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {

    @GetMapping("/all")
    public String getNews(){
        return "news";
    }
}
