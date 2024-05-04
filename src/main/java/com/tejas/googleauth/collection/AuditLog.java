package com.tejas.googleauth.collection;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
@Getter
@Setter
@Document(collection = "audit_logs")
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditLog {
    public AuditLog() {
    }

    @Id
    String _id;

    // request details
    String user_agent;
    String ip;
    String host;

    // user details
    String user_id;
    String meta_data;
    
    @CreatedBy
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;
}
