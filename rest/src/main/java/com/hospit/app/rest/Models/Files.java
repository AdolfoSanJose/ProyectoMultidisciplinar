package com.hospit.app.rest.Models;

import jakarta.persistence.*;

@Entity
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

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public char getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(char extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }
}