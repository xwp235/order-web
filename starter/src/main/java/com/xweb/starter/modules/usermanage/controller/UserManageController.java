package com.xweb.starter.modules.usermanage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManageController {

    @GetMapping("users")
    public Person users(@RequestParam(required = false) Long userId) {
        return new Person("Jack",22);
    }


    public record Person(String username,Integer age) {

    }

}
