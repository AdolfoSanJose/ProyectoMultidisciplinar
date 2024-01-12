package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.Messages;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.MessageRepo;
import com.hospit.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code MessageService} class provides business logic for managing message entries.
 * It is annotated as a Spring service to indicate that it contains business logic.
 */
@Service
public class MessageService {
    /**
     * The Spring Data repository for message entries.
     */
    @Autowired
    private MessageRepo messageRepo;

    /**
     * The Spring Data repository for user entries.
     */
    @Autowired
    private UserRepo userRepo;

    /**
     * Saves a message entry in the database.
     *
     * @param message The message entry to be saved.
     * @return The saved message entry.
     * @throws RuntimeException if the destinatario or remitente is not found in the database.
     */
    public Messages saveMessage(Messages message) {
        User destinatario = userRepo.findByEmail(message.getDestinatario().getEmail());
        User remitente = userRepo.findByEmail(message.getRemitente().getEmail());

        if (destinatario == null || remitente == null) {
            throw new RuntimeException("Destinatario or remitente not found in the database");
        }

        message.setDestinatario(destinatario);
        message.setRemitente(remitente);

        return messageRepo.save(message);
    }

    /**
     * Retrieves a list of messages based on the sender's email address.
     *
     * @param email The email address of the sender.
     * @return A list of messages sent by the specified sender.
     */
    public List<Messages> getMessagesBySender(String email) {
        return messageRepo.findByRemitente_Email(email);
    }
}