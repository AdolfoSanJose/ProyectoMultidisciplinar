package com.hospit.app.rest.Controller;
import com.hospit.app.rest.Models.Messages;
import com.hospit.app.rest.Services.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Esta clase contiene pruebas unitarias para la clase MessageController, centrándose en los métodos relacionados con mensajes.
 */
public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    /**
     * Caso de prueba para verificar que se obtienen correctamente los mensajes por el correo electrónico del remitente.
     */
    @Test
    void testGetMessagesByRemitenteEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String remitenteEmail = "test@example.com";
        List<Messages> expectedMessages = Arrays.asList(new Messages());
        when(messageService.getMessagesBySender(remitenteEmail)).thenReturn(expectedMessages);

        // Act
        ResponseEntity<List<Messages>> response = messageController.getMessagesByRemitenteEmail(
                Collections.singletonMap("remitente", remitenteEmail)
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessages, response.getBody());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de solicitud (BAD REQUEST) cuando no se proporciona el correo electrónico del remitente.
     */
    @Test
    void testGetMessagesByRemitenteEmailBadRequest() {
        MockitoAnnotations.openMocks(this);

        // Act
        ResponseEntity<List<Messages>> response = messageController.getMessagesByRemitenteEmail(Collections.emptyMap());

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de no encontrado (NOT FOUND) cuando no hay mensajes para el remitente proporcionado.
     */
    @Test
    void testGetMessagesByRemitenteEmailNotFound() {
        MockitoAnnotations.openMocks(this);
        String remitenteEmail = "nonexistent@example.com";
        when(messageService.getMessagesBySender(remitenteEmail)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Messages>> response = messageController.getMessagesByRemitenteEmail(
                Collections.singletonMap("remitente", remitenteEmail)
        );

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se guarda correctamente un mensaje.
     */
    @Test
    void testSaveMessage() {
        MockitoAnnotations.openMocks(this);
        Messages messageToSave = new Messages(Long.valueOf(1), null, null, "test message", "test content", null);
        when(messageService.saveMessage(any(Messages.class))).thenReturn(messageToSave);

        // Act
        ResponseEntity<Messages> response = messageController.saveMessage(messageToSave);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageToSave, response.getBody());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de solicitud (BAD REQUEST) cuando se intenta guardar un mensaje nulo.
     */
    @Test
    void testSaveMessageBadRequest() {
        MockitoAnnotations.openMocks(this);

        // Act
        ResponseEntity<Messages> response = messageController.saveMessage(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}