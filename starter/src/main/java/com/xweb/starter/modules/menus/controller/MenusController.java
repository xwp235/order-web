package com.xweb.starter.modules.menus.controller;

import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.menus.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class MenusController {

    private final MenuService menuService;

    @GetMapping
    public JsonResp list() {
         return JsonResp.data(menuService.list());
    }

}
