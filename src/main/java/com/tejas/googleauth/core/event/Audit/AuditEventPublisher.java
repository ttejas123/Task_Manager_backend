package com.tejas.googleauth.core.event.Audit;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tejas.googleauth.model.LogData;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuditEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishGoogleAuthEvent(String meta_data, String user_id) {
        Map<String, String > mapData = new LinkedHashMap<>();
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        mapData.put("user_agent", req.getHeader("User-Agent"));
        mapData.put("ip", req.getHeader("x-forwarded-for"));
        mapData.put("host", req.getHeader("host"));
        mapData.put("meta_data", meta_data);
        mapData.put("user_id", user_id);
        applicationEventPublisher.publishEvent(
            new AuditEvent<Map<String, String>>(mapData)
        );
    }
}
