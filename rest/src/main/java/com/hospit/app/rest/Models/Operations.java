package com.hospit.app.rest.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "operaciones")
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion", unique = true, nullable = false)
    private int idOperacion;
    private String descripcion;

    public Operations() {

    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}