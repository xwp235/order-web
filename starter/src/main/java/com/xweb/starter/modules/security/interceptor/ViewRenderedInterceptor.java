package com.xweb.starter.modules.security.interceptor;

import com.xweb.starter.common.resp.LoginAccountPermissionsResp;
import com.xweb.starter.modules.security.service.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@RequiredArgsConstructor
public class ViewRenderedInterceptor implements HandlerInterceptor {

    private final PermissionService permissionService;

    // 是在视图渲染之后执行的，适合用于请求完成后的资源清理或异常处理。
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }

    // 是在处理完请求之后、视图渲染之前，适合用于修改视图模型或添加额外数据。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        var session = request.getSession(false);
        var permissions = (LoginAccountPermissionsResp) session.getAttribute("permissions");
        if (Objects.isNull(permissions)) {
            permissions = permissionService.getLoginAccountPermissions();
            session.setAttribute("permissions", permissions);
        }
    }

}
