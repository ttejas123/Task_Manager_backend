package com.tejas.googleauth.core.event.Audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tejas.googleauth.model.LogData;
import com.tejas.googleauth.repository.GeneralLogging.AuditLogRepository;
import com.tejas.googleauth.collection.*;

import java.util.*;
import lombok.SneakyThrows;

@Component 
public class AuditEventHandler {

    @Autowired
    AuditLogRepository auditLogRepository;

    @SneakyThrows
    @EventListener 
    @Async 
    public void handleEvent(AuditEvent<Map<String, String>> auditEvent) {
        // System.out.println(
        //     new ObjectMapper()
        //     .writerWithDefaultPrettyPrinter()
        //     .writeValueAsString(auditEvent.getData())
        // );
        // Create AuditLog object from eventData
        Map<String, String> eventData = auditEvent.getData();
        AuditLog auditLog = new AuditLog();
        auditLog.setUser_agent(eventData.get("user_agent"));
        auditLog.setIp(eventData.get("ip"));
        auditLog.setHost(eventData.get("host"));
        auditLog.setMeta_data(eventData.get("meta_data"));
        auditLog.setUser_id(eventData.get("user_id"));
        auditLog.setUpdated_at(new Date());
        auditLog.setCreated_at(new Date());

        // Save the AuditLog object
        auditLogRepository.save(auditLog);
    }












}
