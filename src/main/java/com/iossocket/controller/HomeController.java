package com.iossocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xlzhu on 3/25/16.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/hello")
    public String home() {
        System.out.println("hello world");
        return "success";
    }
}
