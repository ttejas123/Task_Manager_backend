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
@Document(collection = "google_auth_user_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleAuth {
    @Id
    private String _id;

    private List<String> scope = List.of();
    private String google_id_token;
    private String displayname;
    private String user_id;
    private String email;

    @CreatedBy
    private Date created_at = new Date();

    @LastModifiedDate
    private Date updated_at = new Date();
}
