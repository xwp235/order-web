package com.xweb.starter.modules.security;

import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HttpSessionUtil {

    // 用于存储 sessionId 和对应的 HttpSession
    private final static Map<String, HttpSession> HTTP_SESSION_MAP = new ConcurrentHashMap<>();

    // 存储 session
    public static void storeSession(HttpSession session) {
        HTTP_SESSION_MAP.put(session.getId(), session);
    }

    public static HttpSession get(String sessionId){
        return  HTTP_SESSION_MAP.get(sessionId);
    }

    // 检查 session 是否有效
    public static boolean isSessionValid(String sessionId) {
        HttpSession session = HTTP_SESSION_MAP.get(sessionId);
        if (session == null) {
            return false; // Session does not exist
        }

        // Check if the session is expired
        try {
            // Check if the session is expired
            long lastAccessedTime = session.getLastAccessedTime();
            int maxInactiveInterval = session.getMaxInactiveInterval();
            long currentTime = System.currentTimeMillis();
            return (currentTime - lastAccessedTime) < (maxInactiveInterval * 1000L);
        } catch (IllegalStateException e) {
            return false;
        }
    }

    // 使 session 无效
    public static void invalidateSession(String sessionId) {
        HttpSession session = HTTP_SESSION_MAP.remove(sessionId);
        if (session != null && isSessionValid(sessionId) ) {
            session.invalidate();
        }
    }

    // 移除过期的 session
    public static void cleanExpiredSessions() {
        HTTP_SESSION_MAP.entrySet().removeIf(entry -> {
            HttpSession session = entry.getValue();
            return session == null || !session.isNew() && session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000L < System.currentTimeMillis();
        });
    }

    public static Set<String> getAllSessionIds() {
      return HTTP_SESSION_MAP.keySet();
    }

}
