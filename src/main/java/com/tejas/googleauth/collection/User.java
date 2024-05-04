package com.tejas.googleauth.collection;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String _id;
    private String displayname;
    private String profile_picture;
    private Integer age;
    private List<String> scope;
    private String type;
    private String phonenumber; 
}