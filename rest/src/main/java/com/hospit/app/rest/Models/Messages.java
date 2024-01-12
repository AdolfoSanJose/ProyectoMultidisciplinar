package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "mensajes")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;

    @ManyToOne
    @JoinColumn(name = "remitente", referencedColumnName = "correo")
    private User remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario", referencedColumnName = "correo")
    private User destinatario;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha_hora")
    private Date fechaHora;

    public Messages(){

    }

}