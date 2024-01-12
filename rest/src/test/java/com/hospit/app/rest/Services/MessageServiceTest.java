package com.hospit.app.rest.Services;
import com.hospit.app.rest.Models.Messages;
import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.MessageRepo;
import com.hospit.app.rest.Repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Esta clase contiene pruebas unitarias para la clase MessageService, centrándose en los métodos relacionados con mensajes.
 */
public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepo messageRepo;

    @Mock
    private UserRepo userRepo;

    /**
     * Caso de prueba para verificar que se guarda correctamente un mensaje con usuarios válidos (remitente y destinatario).
     */
    @Test
    public void testSaveMessage_ValidUsers() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        User destinatario = new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        User remitente = new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        Messages messageToSave = new Messages(Long.valueOf(1), remitente, destinatario, "test message", "test content", null);
        when(userRepo.findByEmail(destinatario.getEmail())).thenReturn(destinatario);
        when(userRepo.findByEmail(remitente.getEmail())).thenReturn(remitente);
        when(messageRepo.save(any(Messages.class))).thenReturn(messageToSave);

        // Act
        Messages result = messageService.saveMessage(messageToSave);

        // Assert
        assertEquals(messageToSave, result);
    }

    /**
     * Caso de prueba para verificar que se maneja adecuadamente el intento de guardar un mensaje con un destinatario no válido.
     */
    @Test
    public void testSaveMessage_InvalidDestinatario() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        User remitente = new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        Messages invalidMessage = new Messages(Long.valueOf(1), null, null, "test message", "test content", null);
        when(userRepo.findByEmail(any(String.class))).thenReturn(null); // Simula que no se encuentra el usuario

        // Act and Assert
        assertThrows(RuntimeException.class, () -> messageService.saveMessage(invalidMessage));
    }

    /**
     * Caso de prueba para verificar que se obtienen correctamente los mensajes por el correo electrónico del remitente.
     */
    @Test
    public void testGetMessagesBySender_ValidEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String senderEmail = "sender@example.com";
        List<Messages> expectedMessages = Arrays.asList(
                new Messages(Long.valueOf(1), null, null, "test message", "test content", null));
        when(messageRepo.findByRemitente_Email(senderEmail)).thenReturn(expectedMessages);

        // Act
        List<Messages> result = messageService.getMessagesBySender(senderEmail);

        // Assert
        assertEquals(expectedMessages, result);
    }

    /**
     * Caso de prueba para verificar que se recibe una lista vacía cuando se busca mensajes con un correo electrónico de remitente no válido.
     */
    @Test
    public void testGetMessagesBySender_InvalidEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String invalidEmail = "invalid@example.com";
        when(messageRepo.findByRemitente_Email(invalidEmail)).thenReturn(Collections.emptyList());

        // Act
        List<Messages> result = messageService.getMessagesBySender(invalidEmail);

        // Assert
        assertTrue(result.isEmpty());
    }
}