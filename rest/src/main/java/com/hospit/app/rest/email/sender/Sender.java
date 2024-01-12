package com.hospit.app.rest.email.sender;

import com.hospit.app.rest.email.sender.enums.MailCredentialProperties;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.Setter;

import jakarta.mail.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code Sender} class is responsible for sending emails using JavaMail API.
 * It loads mail and credential properties from configuration files and provides a method for sending emails.
 */
@Component
public class Sender {

    /**
     * Properties for mail configuration.
     */
    @Setter
    @Getter
    Properties mailProp = new Properties();

    /**
     * Properties for credential configuration.
     */
    @Setter
    @Getter
    Properties credentialProp = new Properties();

    /**
     * Constructs a {@code Sender} object and loads mail and credential properties from configuration files.
     */
    public Sender() {
        try {
            // Loads all the properties from the "mail.properties" file.
            mailProp.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));

            // Loads all the properties from the "credentials.properties" file.
            credentialProp.load(getClass().getClassLoader().getResourceAsStream("credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends an email with the specified recipient, subject, and content.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param content The content or body of the email.
     * @return {@code true} if the email is sent successfully, {@code false} otherwise.
     */
    public boolean send(String to, String subject, String content) {
        // Get the Session object and pass username and password.
        Session session = createSession();

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set the From: header field of the header.
            message.setFrom(new InternetAddress(credentialProp.getProperty(MailCredentialProperties.USER.getName())));

            // Set the To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set the Subject: header field.
            message.setSubject(subject);

            // Set the actual message content.
            message.setContent(content, "text/html");

            System.out.println("Sending...");
            // Send the message.
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a Session object for email configuration, including authentication.
     *
     * @return The configured Session object.
     */
    private Session createSession() {
        Session session = Session.getInstance(mailProp, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        credentialProp.getProperty(MailCredentialProperties.USER.getName()),
                        credentialProp.getProperty(MailCredentialProperties.PASSWORD.getName())
                );
            }
        });

        // Enable debugging for SMTP issues.
        session.setDebug(true);
        return session;
    }
}