package com.asistencia.controllers;

import com.asistencia.models.Asistencia;
import com.asistencia.models.Horario;
import com.asistencia.models.User;
import com.asistencia.repositories.AsistenciaRepository;
import com.asistencia.repositories.HorarioRepository;
import com.asistencia.repositories.UserRepository;
import com.asistencia.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    // Método para obtener todas las asistencias
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaService.getAllAsistencias();
    }

    // Método para obtener una asistencia por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.getAsistenciaById(id);
        return asistencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para crear una nueva asistencia
    @PostMapping
    public ResponseEntity<Asistencia> createAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.createAsistencia(asistencia);
        return ResponseEntity.ok(nuevaAsistencia);
    }

    // Método para actualizar una asistencia existente
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        Optional<Asistencia> asistenciaExistente = asistenciaService.getAsistenciaById(id);
        if (asistenciaExistente.isPresent()) {
            Asistencia updatedAsistencia = asistenciaService.updateAsistencia(id, asistencia);
            return ResponseEntity.ok(updatedAsistencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para eliminar una asistencia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long id) {
        boolean eliminado = asistenciaService.deleteAsistencia(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/marcar")
    public ResponseEntity<Asistencia> obtenerAsistenciasDeHoy() {
        System.out.println("Accediendo al endpoint '/marcar'...");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("no se encontro al usuario");
            return ResponseEntity.notFound().build();
        }
        //lista donde marco entrada y no marco salida
        List<Asistencia> asistencias = asistenciaRepository.findByUserIdAndHoraIngresoIsNotNullAndHoraSalidaIsNull(user.getId());
        //si encontró que falta marcar salida entonces marcara ahi y terminará.
        if (asistencias.size()>0) {
            System.out.println("se encontro asistencias");
            for (Asistencia asistencia : asistencias) {
                System.out.println("for de las asistencias encontradas");
                asistencia.setHora_salida(LocalTime.now());
                asistenciaRepository.save(asistencia);
                System.out.println("se marco salida");
                return ResponseEntity.ok(asistencia);//marcó salida
            }
            System.out.println("se va a ir sin hacer nada");
        } else{//lista de las asistencias que tiene que marcar hoy
            List<Asistencia> asistenciasHoy = asistenciaRepository.findByUserIdAndFecha(user.getId(), LocalDate.now());
            if (asistenciasHoy != null) {//tengo que marcar asistencia hoy??
                System.out.println("asistencia hoy marcar");
                LocalTime este_momento = LocalTime.now();
                List<Horario> horarios =new ArrayList<>();
                for (Asistencia asistencia : asistenciasHoy) {//se buscan todos los horarios
                    horarios.add(asistencia.getHorario());
                }
                //ya tengo todos los horarios, ahora a comparar con cual toca
                Horario horarioFinal = null;
                for (Horario horario : horarios) {
                    //llega 15 minutos antes de que le toque
                    LocalTime horarioInicioConMargen = horario.getHorarioInicio().minusMinutes(15);
                    if (este_momento.isBefore(horario.getHorarioFin()) && este_momento.isAfter(horarioInicioConMargen)) {
                        // Si se cumple la condición, encontramos el horario que cumple los criterios
                        horarioFinal = horario;
                        // Termina el bucle for
                    }
                }
                if (horarioFinal != null) {//si el anterior for encontro un horario que le toca marcar
                    System.out.println("marcar en el horario que se encontro");
                    // margen 15 minutos tarde
                    LocalTime horarioInicioConMargenRetraso = horarioFinal.getHorarioInicio().plusMinutes(5);
                    //se busca la asistencia a marcar, con el id del usuario, id del horario y la fecha de hoy
                    Asistencia aMarcar = asistenciaRepository.findByUserIdAndHorarioIdAndFecha(user.getId(), horarioFinal.getId(), LocalDate.now());
                    String cadena2 = new String("presente");
                    if(aMarcar.getTipo().equals(cadena2)){
                        return ResponseEntity.notFound().build();
                    }
                    else{
                        aMarcar.setHora_ingreso(LocalTime.now());
                        if (LocalTime.now().isAfter(horarioInicioConMargenRetraso)) {

                            aMarcar.setTipo("atraso");
                        }else {
                            aMarcar.setTipo("presente");
                        }
                        long minutosAtraso = horarioFinal.getHorarioInicio().until(LocalTime.now(), ChronoUnit.MINUTES);
                        if (minutosAtraso > 0) {
                            aMarcar.setMinutos_atraso((int) minutosAtraso);
                        }
                        asistenciaRepository.save(aMarcar);
                        return ResponseEntity.ok(aMarcar);
                    }

                }else{// si no encontro un horario para hoy
                    System.out.println("no se encontro un horario");
                    return ResponseEntity.notFound().build();
                }
            }//si en esta fecha no tiene nada que marcar
            System.out.println("fecha hoy no se marca nada");
            return ResponseEntity.notFound().build();
        }
        System.out.println("no entro a nada");
        return ResponseEntity.notFound().build();
    }
}
