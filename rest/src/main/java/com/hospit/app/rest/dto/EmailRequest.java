package com.hospit.app.rest.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String content;
}