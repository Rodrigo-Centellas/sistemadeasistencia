package com.asistencia.models;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String rol;
    private String ci;
    private String direccion;

    //relacion Con clase
    @OneToMany(mappedBy = "user")
    private Set<Clase> clases;


}
