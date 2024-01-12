package com.hospit.app.rest.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true, nullable = false)
    private Long idUser;

    @Column(name = "nombre")
    private String name;

    @Column(name = "correo", unique = true)
    private String email;

    @Column(name = "contrasegna")
    private String password;

    @Column
    private String dni;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Roles idRol;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private User medico;

    public User() {
    }

}
