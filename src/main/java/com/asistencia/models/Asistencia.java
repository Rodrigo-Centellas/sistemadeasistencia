package com.asistencia.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencias")
@Data
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private LocalDateTime hora;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
