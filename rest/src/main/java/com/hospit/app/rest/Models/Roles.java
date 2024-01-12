package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code Roles} entity class represents roles in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "roles" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
public class Roles {
    /**
     * The unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", unique = true, nullable = false)
    private int idRol;

    /**
     * The description or name of the role.
     */
    private String descripcion;

    /**
     * Default constructor for the {@code Roles} entity.
     * Required for JPA persistence.
     */
    public Roles() {
        // Default constructor
    }
}