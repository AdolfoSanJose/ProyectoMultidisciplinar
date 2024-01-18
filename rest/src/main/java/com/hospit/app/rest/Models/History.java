package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code History} entity class represents medical appointment histories in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "historiales" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "historiales")
public class History {
    /**
     * The unique identifier for the medical appointment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    /**
     * The patient associated with the medical appointment.
     */
    @ManyToOne
    @JoinColumn(name = "paciente")
    private User paciente;

    /**
     * The doctor associated with the medical appointment.
     */
    @ManyToOne
    @JoinColumn(name = "medico")
    private User medico;

    /**
     * The specialty related to the medical appointment.
     */
    @Column(name = "especialidad")
    private String especialidad;

    /**
     * The reason or motive for the medical appointment.
     */
    @Column(name = "motivo")
    private String motivo;

    /**
     * The date and time of the medical appointment.
     */
    @Column(name = "fecha_hora")
    private Date fechaHora;

    /**
     * Default constructor for the {@code History} entity.
     * Required for JPA persistence.
     */
    public History() {
        // Default constructor
    }
}