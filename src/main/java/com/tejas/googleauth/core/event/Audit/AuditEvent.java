package com.tejas.googleauth.core.event.Audit;
import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class AuditEvent<T> extends ApplicationEvent {

    private T data;

    public AuditEvent(T event) {
        super(event);
        this.data = event;
    }
    
}
