package com.hospit.app.rest.Controller;

import com.hospit.app.rest.Models.Messages;
import com.hospit.app.rest.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code MessageController} class is a Spring MVC controller that handles message-related requests.
 * It provides endpoints for retrieving messages by sender email and saving new messages.
 */
@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {

    /**
     * The service responsible for handling message-related operations.
     */
    @Autowired
    MessageService messageService;

    /**
     * Handles the HTTP POST request to retrieve messages based on a specified sender email.
     *
     * @param requestBody A {@code Map} containing the request body with the "remitente" parameter.
     * @return A {@code ResponseEntity} containing a list of messages if successful, or an error response otherwise.
     */
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

    /**
     * Handles the HTTP POST request to save a new message.
     *
     * @param message The {@code Messages} object representing the message to be saved.
     * @return A {@code ResponseEntity} containing the saved message if successful, or an error response otherwise.
     */
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

