package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
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
}