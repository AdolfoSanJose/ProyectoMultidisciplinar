package com.hospit.app.rest.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "operaciones")
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion", unique = true, nullable = false)
    private int idOperation;
    private String descripcion;

    public Operations() {

    }

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}