package com.hospit.app.rest.Controller;

import com.hospit.app.rest.dto.EmailRequest;
import com.hospit.app.rest.email.sender.Sender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/email")
public class EmailController {

    private final Sender emailSender;

    public EmailController(Sender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/send")
    public boolean sendEmail(@RequestBody EmailRequest emailRequest) {
        return emailSender.send(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getContent());
    }
}
