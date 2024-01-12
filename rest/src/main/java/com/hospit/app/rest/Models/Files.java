package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "archivos")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo", unique = true, nullable = false)
    private int idFile;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "extension_archivo", length = 5)
    private char extensionArchivo;
    public Files() {

    }

}