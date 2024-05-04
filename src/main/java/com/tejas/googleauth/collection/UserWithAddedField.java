package com.tejas.googleauth.collection;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWithAddedField {
    private String _id;
    private String displayname;
    private String profile_picture;
    private Integer age;
    private List<String> scope;
    private String type;
    private String test;
    private String phonenumber; 
}
