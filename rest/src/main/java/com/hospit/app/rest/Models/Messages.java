package com.hospit.app.rest.Models;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.Date;

@Entity
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

    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public User getRemitente() {
        return remitente;
    }

    public void setRemitente(User remitente) {
        this.remitente = remitente;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(User destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}