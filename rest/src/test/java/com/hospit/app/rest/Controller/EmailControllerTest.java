package com.hospit.app.rest.Controller;

import com.hospit.app.rest.dto.EmailRequest;
import com.hospit.app.rest.email.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Esta clase contiene pruebas unitarias para la clase EmailController, centrándose específicamente en el método sendEmail.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private Sender emailSender;

    /**
     * Caso de prueba para verificar que el método sendEmail envía correctamente un correo electrónico utilizando el EmailRequest proporcionado.
     */
    @Test
    public void testSendEmail() {
        // Suponiendo que el constructor de EmailRequest acepta tres parámetros: to, subject, content
        EmailRequest emailRequest = new EmailRequest("to@example.com", "Asunto", "Contenido");

        // Simula que emailSender siempre devuelve true (simulando un envío de correo electrónico exitoso)
        when(emailSender.send(anyString(), anyString(), anyString())).thenReturn(true);

        // Llama al método sendEmail y asegura que devuelva true
        boolean result = emailController.sendEmail(emailRequest);
        assertTrue(result);

        // Verifica que el método emailSender.send fue llamado con los argumentos correctos
        verify(emailSender).send(eq("to@example.com"), eq("Asunto"), eq("Contenido"));
    }

    /**
     * Caso de prueba para verificar que el método sendEmail maneja el caso en que falla el envío del correo electrónico.
     */
    @Test
    public void testSendEmail_Incorrecto() {
        // Suponiendo que el constructor de EmailRequest acepta tres parámetros: to, subject, content
        EmailRequest emailRequest = new EmailRequest("to@example.com", "Asunto", "Contenido");

        // Simula que emailSender siempre devuelve false (simulando un envío de correo electrónico fallido)
        when(emailSender.send(anyString(), anyString(), anyString())).thenReturn(false);

        // Llama al método sendEmail y asegura que devuelva false
        boolean result = emailController.sendEmail(emailRequest);
        assertFalse(result);

        // Verifica que el método emailSender.send fue llamado con los argumentos correctos
        verify(emailSender).send(eq("to@example.com"), eq("Asunto"), eq("Contenido"));
    }
}
