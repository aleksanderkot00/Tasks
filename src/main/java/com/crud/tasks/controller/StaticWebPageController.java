package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String,Object> map) {
        map.put("variable", "My Thymeleaf variable");
        map.put("one", 1);
        map.put("two", 2);
        return "index";
    }
}
