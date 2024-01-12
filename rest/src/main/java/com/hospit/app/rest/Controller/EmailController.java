package com.hospit.app.rest.Controller;

import com.hospit.app.rest.dto.EmailRequest;
import com.hospit.app.rest.email.sender.Sender;
import org.springframework.web.bind.annotation.*;


/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code EmailController} class is a Spring MVC controller that handles email-related requests.
 * It exposes an endpoint for sending emails via the configured email sender.
 */
@RestController
@CrossOrigin
@RequestMapping("/api/email")
public class EmailController {

    /**
     * The email sender responsible for sending emails.
     */
    private final Sender emailSender;

    /**
     * Constructs an {@code EmailController} with the specified email sender.
     *
     * @param emailSender The email sender to be used for sending emails.
     */
    public EmailController(Sender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Handles the HTTP POST request to send an email.
     *
     * @param emailRequest The {@code EmailRequest} containing the email details (recipient, subject, content).
     * @return {@code true} if the email is sent successfully, {@code false} otherwise.
     */
    @PostMapping("/send")
    public boolean sendEmail(@RequestBody EmailRequest emailRequest) {
        return emailSender.send(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getContent());
    }
}