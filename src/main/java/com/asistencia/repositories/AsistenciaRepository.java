package com.asistencia.repositories;

import com.asistencia.models.Asistencia;
import com.asistencia.models.Horario;
import com.asistencia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a WHERE a.user.id = :userId AND a.fecha = :fecha")
    List<Asistencia> findByUserIdAndFecha(Long userId, LocalDate fecha);

    @Query("SELECT a FROM Asistencia a WHERE a.user.id = 1 AND a.hora_ingreso IS NOT NULL AND a.hora_salida IS NULL")
    List<Asistencia> findByUserIdAndHoraIngresoIsNotNullAndHoraSalidaIsNull(Long userId);

    @Query("SELECT a FROM Asistencia a WHERE a.user.id = :userId AND a.horario.id = :horarioId AND a.fecha = :fecha")
    Asistencia findByUserIdAndHorarioIdAndFecha(@Param("userId") Long userId, @Param("horarioId") Long horarioId, @Param("fecha") LocalDate fecha);


}
