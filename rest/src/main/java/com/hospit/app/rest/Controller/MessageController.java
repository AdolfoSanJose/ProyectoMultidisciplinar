package com.hospit.app.rest.Controller;

import com.hospit.app.rest.Models.Messages;
import com.hospit.app.rest.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService messageService;


    @PostMapping("/getMessagesByRemitente")
    public ResponseEntity<List<Messages>> getMessagesByRemitenteEmail(@RequestBody Map<String, String> requestBody) {
        String remitenteEmail = requestBody.get("remitente");

        if (remitenteEmail != null) {
            List<Messages> messages = messageService.getMessagesBySender(remitenteEmail);

            if (!messages.isEmpty()) {
                return ResponseEntity.ok(messages);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Messages> saveLog(@RequestBody Messages message) {
        Messages savedMessage = messageService.saveMessage(message);

        if (savedMessage != null) {
            return ResponseEntity.ok(savedMessage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

