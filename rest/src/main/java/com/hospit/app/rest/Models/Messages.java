package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.Date;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code Messages} entity class represents messages in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "mensajes" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "mensajes")
public class Messages {
    /**
     * The unique identifier for the message.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;

    /**
     * The sender of the message.
     */
    @ManyToOne
    @JoinColumn(name = "remitente", referencedColumnName = "correo")
    private User remitente;

    /**
     * The recipient of the message.
     */
    @ManyToOne
    @JoinColumn(name = "destinatario", referencedColumnName = "correo")
    private User destinatario;

    /**
     * The subject of the message.
     */
    @Column(name = "asunto")
    private String asunto;

    /**
     * The content or body of the message.
     */
    @Column(name = "contenido")
    private String contenido;

    /**
     * The date and time of the message.
     */
    @Column(name = "fecha_hora")
    private Date fechaHora;

    /**
     * Default constructor for the {@code Messages} entity.
     * Required for JPA persistence.
     */
    public Messages() {
        // Default constructor
    }

    public Messages(Long idMensaje, User remitente, User destinatario, String asunto, String contenido, Date fechaHora) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
    }
}