package com.tejas.googleauth.collection;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import java.util.*;

@Data
@Builder
@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailStruct {
    
    @Id
    private String _id;
    private String subject;
    private String to;
    private String from;
    private String cc="";
    private String body="";
    
    @CreatedBy
    private Date created_at = new Date();

    @LastModifiedDate
    private Date updated_at = new Date();
}
