package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code User} entity class represents users in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "usuarios" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true, nullable = false)
    private Long idUser;

    /**
     * The name of the user.
     */
    @Column(name = "nombre")
    private String name;

    /**
     * The email address of the user.
     */
    @Column(name = "correo", unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "contrasegna")
    private String password;

    /**
     * The DNI (Documento Nacional de Identidad) of the user.
     */
    @Column
    private String dni;

    /**
     * The role associated with the user.
     */
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Roles idRol;

    /**
     * The medical professional associated with the user, if applicable.
     */
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private User medico;

    /**
     * Default constructor for the {@code User} entity.
     * Required for JPA persistence.
     */
    public User() {
        // Default constructor
    }
}