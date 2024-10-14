package com.xweb.starter.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class RequestUtil {

    /**
     * 获取请求上下文路径
     */
    public static String getContextPath() {
        var contextPath = "";
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            var request = attributes.getRequest();
            contextPath = request.getContextPath();
        }
        return contextPath;
    }

    /**
     * 获取请求中的参数值
     */
    public static String getRequestParameter(String name) {
        String value = null;
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            var request = attributes.getRequest();
            value = request.getParameter(name);
        }
        return value;
    }

    /**
     * 获取Servlet请求对象
     */
    public static HttpServletRequest getServletRequest() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            return attributes.getRequest();
        }
        return null;
    }

    /**
     * 获取Servlet返回对象
     */
    public static HttpServletResponse getServletResponse() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            return attributes.getResponse();
        }
        return null;
    }

    /**
     * 获取请求中的属性值
     */
    public static Object getRequestAttribute(String name) {
        Object value = null;
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            RequestContextHolder.setRequestAttributes(attributes,true);
            var request = attributes.getRequest();
            value = request.getAttribute(name);
        }
        return value;
    }

    public final static String LOCAL_IP = "127.0.0.1";

    public final static String LOCAL_ADDRESS = "0:0:0:0:0:0:0:1";
    /**
     * 获取客户端IP
     *
     * <p>
     * 默认检测的Header:
     *
     * <pre>
     * 1、X-Forwarded-For
     * 2、X-Real-IP
     * 3、Proxy-Client-IP
     * 4、WL-Proxy-Client-IP
     * </pre>
     *
     * <p>
     * otherHeaderNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request          请求对象{@link HttpServletRequest}
     * @param otherHeaderNames 其他自定义头文件，通常在Http服务器（例如Nginx）中配置
     * @return IP地址
     */
    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        if (ArrayUtils.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtils.addAll(headers, otherHeaderNames);
        }

        String ip = null;
        for (String header : headers) {
            String currentIp = request.getHeader(header);
            if (isNotUnknown(currentIp)) {
                ip = currentIp;
                break;
            }
        }
        if (null == ip) {
            ip = request.getRemoteAddr();
        }
        if (LOCAL_ADDRESS.equals(ip)) {
            return LOCAL_IP;
        }
        return getClientIPByHeader(request);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    private static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        String delimiter = ",";
        if (ip != null && ip.indexOf(delimiter) > 0) {
            String[] ips = ip.trim().split(delimiter);
            for (String subIp : ips) {
                if (isNotUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    private static boolean isNotUnknown(String checkIp) {
        return StringUtils.isNotBlank(checkIp)  && !"unknown".equalsIgnoreCase(checkIp);
    }

    /**
     * 获取客户端IP
     *
     * <p>
     * headerNames参数用于自定义检测的Header<br>
     * 需要注意的是，使用此方法获取的客户IP地址必须在Http服务器（例如Nginx）中配置头信息，否则容易造成IP伪造。
     * </p>
     *
     * @param request     请求对象{@link HttpServletRequest}
     * @return IP地址
     */
    public static String getClientIPByHeader(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("XMLHttpRequest", request.getHeader("X-Requested-With"));
    }

}
