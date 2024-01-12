package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "historiales")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private User paciente;

    @ManyToOne
    @JoinColumn(name = "medico")
    private User medico;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "fecha_hora")
    private Date fechaHora;

    public History(){

    }

}