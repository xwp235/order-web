package com.xweb.starter.modules.security;

import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SecureUserHolder {

    private static final Map<String,Map<String, Set<SecureUserSession>>> SESSIONS = new ConcurrentHashMap<>();

    public static void put(Authentication authentication) {
        var loginExtraDetails = (LoginExtraDetails)authentication.getDetails();
        var device = loginExtraDetails.getDevice();
        var sessionId = loginExtraDetails.getSessionId();

        Assert.notNull(sessionId,"Authenticated user's session id is null");

        var ipAddress = loginExtraDetails.getRemoteAddress();
        var clientIp = Objects.nonNull(ipAddress) && ipAddress.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1": ipAddress;

        var secureUser = (SecureUser)authentication.getPrincipal();

        Assert.notNull(secureUser,"secureUser is null");

        var username = secureUser.getUsername();
        var accountId = secureUser.getAccountId();

        var secureUserSession = new SecureUserSession();
        secureUserSession.setDevice(device);
        secureUserSession.setUsername(username);
        secureUserSession.setSessionId(sessionId);
        secureUserSession.setAccountId(accountId);
        secureUserSession.setClientIp(clientIp);
        secureUserSession.setAuthentication(authentication);

        var session = SESSIONS.get(username);
        if (MapUtils.isEmpty(session)) {
            var deviceMap = new HashMap<String,Set<SecureUserSession>>();
            var clientSet = new HashSet<SecureUserSession>();
            clientSet.add(secureUserSession);
            deviceMap.put(device, clientSet);
            SESSIONS.put(username, deviceMap);
        } else {
            var clientList = session.get(username);
            if (CollectionUtils.isEmpty(clientList)) {
                clientList = new HashSet<>();
                clientList.add(secureUserSession);
                session.put(device, clientList);
            } else {
                clientList.add(secureUserSession);
            }
        }
    }

    public static Authentication get(String username, String device,String sessionId) {
        var session = SESSIONS.get(username);
        if (MapUtils.isEmpty(session)) {
            return null;
        }
        var deviceSet = session.get(device);
        if (CollectionUtils.isEmpty(deviceSet)) {
            return null;
        }
        for (var secureUserSession : deviceSet) {
            if (StringUtils.equals(secureUserSession.getSessionId(),sessionId)) {
                return secureUserSession.getAuthentication();
            }
        }
        return null;
    }

    public static void removeByUsername(String username) {
        var session = SESSIONS.get(username);
        if (MapUtils.isEmpty(session)) {
            return;
        }
        SESSIONS.remove(username);
    }

    public static void removeByDevice(String username, String device) {
        var session = SESSIONS.get(username);
        if (MapUtils.isEmpty(session)) {
            return;
        }
        session.remove(device);
        if (MapUtils.isEmpty(session)){
            SESSIONS.remove(username);
        }
    }

    public static void remove(String username, String device, String sessionId) {
        var session = SESSIONS.get(username);
        if (MapUtils.isEmpty(session)) {
            return;
        }
        var deviceSet = session.get(device);
        if (CollectionUtils.isEmpty(deviceSet)) {
            return;
        }
        deviceSet.removeIf(deviceSession -> StringUtils.equals(deviceSession.getSessionId(), sessionId));
        if (CollectionUtils.isEmpty(deviceSet)) {
            session.remove(device);
        }
        if (MapUtils.isEmpty(session)) {
            SESSIONS.remove(username);
        }
    }

    public static void removeBySessionId(String sessionId) {
        if (MapUtils.isEmpty(SESSIONS)) {
            return;
        }

        var sessionIterator = SESSIONS.entrySet().iterator();
        while (sessionIterator.hasNext()) {
            var deviceEntrySet = sessionIterator.next();
            var deviceMap = deviceEntrySet.getValue();
            if (MapUtils.isEmpty(deviceMap)) {
                continue;
            }

            var deviceIterator = deviceMap.entrySet().iterator();
            while (deviceIterator.hasNext()) {
                var deviceEntry = deviceIterator.next();
                var deviceSet = deviceEntry.getValue();
                if (CollectionUtils.isEmpty(deviceSet)) {
                    continue;
                }
                // 使用显式迭代器来避免 ConcurrentModificationException
                deviceSet.removeIf(deviceSession -> StringUtils.equals(deviceSession.getSessionId(), sessionId));
                if (CollectionUtils.isEmpty(deviceSet)) {
                    deviceIterator.remove();
                }
            }

            if (MapUtils.isEmpty(deviceMap)) {
                sessionIterator.remove();
            }
        }
    }

    public static Map<String,Set<SecureUserSession>> getDevicesByUsername(String username) {
        return SESSIONS.get(username);
    }

    public static Map<String, Map<String,Set<SecureUserSession>>> get() {
        return SESSIONS;
    }

    public static Set<String> getAllSessionIds() {
        var sessionIds = new HashSet<String>();
        for (var deviceEntrySet: SESSIONS.entrySet()) {
            var deviceMap = deviceEntrySet.getValue();
            if (MapUtils.isEmpty(deviceMap)) {
                continue;
            }
            for (var everyDevices : deviceMap.entrySet()) {
                var deviceSet = everyDevices.getValue();
                if (CollectionUtils.isEmpty(deviceSet)) {
                    continue;
                }
                for (SecureUserSession secureUserSession : deviceSet) {
                    sessionIds.add(secureUserSession.getSessionId());
                }
            }
        }
        return sessionIds;
    }

}
