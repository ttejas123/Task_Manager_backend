package com.tejas.googleauth.model;

import com.google.auto.value.AutoValue.Builder;

import lombok.Data;

@Data
@Builder
public class ResponseFilter {
    public int limit;
    public int page;
}
