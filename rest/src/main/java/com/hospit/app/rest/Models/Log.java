package com.hospit.app.rest.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log", unique = true, nullable = false)
    private int idLog;
    private Date fechaHora;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User idUser;
    @ManyToOne
    @JoinColumn(name = "id_operacion")
    private Operations idOperation;
    @ManyToOne
    @JoinColumn(name = "id_archivo")
    private Files idFile;

    public Log() {

    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Operations getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Operations idOperation) {
        this.idOperation = idOperation;
    }

    public Files getIdFile() {
        return idFile;
    }

    public void setIdFile(Files idFile) {
        this.idFile = idFile;
    }
}