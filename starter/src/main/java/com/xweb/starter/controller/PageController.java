package com.xweb.starter.controller;

import com.xweb.starter.common.resp.LoginAccountPermissionsResp;
import com.xweb.starter.modules.security.domain.vo.MenuVO;
import com.xweb.starter.modules.security.service.PermissionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final PermissionService permissionService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        var permissions = (LoginAccountPermissionsResp) session.getAttribute("permissions");
        if (Objects.isNull(permissions)) {
            permissions = permissionService.getLoginAccountPermissions();
            session.setAttribute("permissions", permissions);
        }
        return "backend/index";
    }

    @GetMapping("/user-manage")
    public String userManage() {
        return "backend/user-manage/index";
    }

}
