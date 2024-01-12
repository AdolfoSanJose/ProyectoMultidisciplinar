package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code Files} entity class represents files in a system.
 * It is annotated as an entity for JPA persistence and is associated with the "archivos" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "archivos")
public class Files {
    /**
     * The unique identifier for the file.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo", unique = true, nullable = false)
    private int idFile;

    /**
     * The name of the file.
     */
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    /**
     * The extension of the file.
     */
    @Column(name = "extension_archivo", length = 5)
    private char extensionArchivo;

    /**
     * Default constructor for the {@code Files} entity.
     * Required for JPA persistence.
     */
    public Files() {
        // Default constructor
    }
}