package com.hospit.app.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code EmailRequest} class represents a data transfer object (DTO) for email-related requests.
 * It encapsulates the necessary information for sending an email, including the recipient, subject, and content.
 */
@Data
@Getter
@Setter
public class EmailRequest {
    /**
     * The email address of the recipient.
     */
    private String to;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The content or body of the email.
     */
    private String content;

    public EmailRequest(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
}
