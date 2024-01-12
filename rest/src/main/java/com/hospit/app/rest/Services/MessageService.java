package com.hospit.app.rest.Services;

import com.hospit.app.rest.Models.Messages;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.MessageRepo;
import com.hospit.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepo messageRepo;

    @Autowired
    UserRepo userRepo;

    public Messages saveMessage(Messages message) {

        User destinatario = userRepo.findByEmail(message.getDestinatario().getEmail());
        User remitente = userRepo.findByEmail(message.getRemitente().getEmail());

        if (destinatario == null || remitente == null) {
            throw new RuntimeException("Destinatario or remitente no encontrado en la base de datos");
        }

        message.setDestinatario(destinatario);
        message.setRemitente(remitente);

        return messageRepo.save(message);
    }
    public List<Messages> getMessagesBySender(String email) {
        return messageRepo.findByRemitente_Email(email);
    }
}
