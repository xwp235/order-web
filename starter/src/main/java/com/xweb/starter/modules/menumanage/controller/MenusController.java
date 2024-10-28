package com.xweb.starter.modules.menumanage.controller;

import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.menumanage.service.MenuService;
import com.xweb.starter.modules.security.domain.entity.MastMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = {
            RequestMethod.PUT,
            RequestMethod.POST
    })
    public JsonResp saveOrUpdate(MastMenu menu) {
         menuService.saveOrUpdate(menu);
         return JsonResp.ok();
    }

}
