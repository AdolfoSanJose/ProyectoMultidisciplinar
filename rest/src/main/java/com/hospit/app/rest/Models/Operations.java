package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code Operations} entity class represents operations or actions in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "operaciones" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "operaciones")
public class Operations {
    /**
     * The unique identifier for the operation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion", unique = true, nullable = false)
    private int idOperation;

    /**
     * The description or name of the operation.
     */
    private String descripcion;

    /**
     * Default constructor for the {@code Operations} entity.
     * Required for JPA persistence.
     */
    public Operations() {
        // Default constructor
    }
}