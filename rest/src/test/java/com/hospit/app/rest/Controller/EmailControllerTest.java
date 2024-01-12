package com.hospit.app.rest.Controller;

import com.hospit.app.rest.dto.EmailRequest;
import com.hospit.app.rest.email.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private Sender emailSender;

    @Test
    public void testSendEmail() {
        // Assuming EmailRequest constructor accepts three parameters: to, subject, content
        EmailRequest emailRequest = new EmailRequest("to@example.com", "Subject", "Content");

        when(emailSender.send(anyString(), anyString(), anyString())).thenReturn(true);

        boolean result = emailController.sendEmail(emailRequest);

        assertTrue(result);

        // Verify that the emailSender.send method was called with the correct arguments
        verify(emailSender).send(eq("to@example.com"), eq("Subject"), eq("Content"));
    }
}