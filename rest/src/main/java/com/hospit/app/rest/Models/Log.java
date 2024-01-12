package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code Log} entity class represents log entries in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "log" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "log")
public class Log {
    /**
     * The unique identifier for the log entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log", unique = true, nullable = false)
    private int idLog;

    /**
     * The date and time of the log entry.
     */
    private Date fechaHora;

    /**
     * The user associated with the log entry.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User idUser;

    /**
     * The operation associated with the log entry.
     */
    @ManyToOne
    @JoinColumn(name = "id_operacion")
    private Operations idOperation;

    /**
     * The file associated with the log entry.
     */
    @ManyToOne
    @JoinColumn(name = "id_archivo")
    private Files idFile;

    /**
     * Default constructor for the {@code Log} entity.
     * Required for JPA persistence.
     */
    public Log() {
        // Default constructor
    }

    public Log(int idLog, Date fechaHora, User idUser, Operations idOperation, Files idFile) {
        this.idLog = idLog;
        this.fechaHora = fechaHora;
        this.idUser = idUser;
        this.idOperation = idOperation;
        this.idFile = idFile;
    }
}