package me.study.jpa.v1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@Slf4j
//@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        log.info("home controller");
        return "home";
    }
}
