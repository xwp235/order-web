package com.xweb.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "backend/index";
    }

    @GetMapping("/user-manage")
    public String userManage() {
        return "backend/user-manage/index";
    }

    @GetMapping("/menu-manage")
    public String menuManage() {
        return "backend/menu-manage/index";
    }

}
