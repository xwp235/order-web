package com.xweb.starter.controller;

import com.xweb.starter.modules.menumanage.service.MenuService;
import com.xweb.starter.modules.security.domain.entity.MastMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final MenuService menuService;

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

    @GetMapping({"{module}/form/{id:\\d+}","{module}/form"})
    public String form(@PathVariable String module,@PathVariable(required = false) Integer id,Model model) {
        var tplPath = "";
        switch (module) {
            case "menus" -> {
                MastMenu menu;
                tplPath = "/menu-manage/menu-form";
                if (Objects.nonNull(id)) {
                    menu = menuService.getById(id);
//                    var parentId = menu.getMmParentId();
//                String parentName = null;
//                if (Objects.nonNull(parentId)) {
//                    parentName = menuService.getById(parentId).getMmName().getZh_CN();
//                }
                    model.addAttribute("menu", menu);
                } else {
                    menu = new MastMenu();
                }
                model.addAttribute("menu", menu);
//                model.addAttribute("parentName",parentName);
            }
            case "users" -> tplPath = "/user-manage/user-form";
            default -> tplPath= "";
        }
        return "backend"+tplPath;
    }

}
