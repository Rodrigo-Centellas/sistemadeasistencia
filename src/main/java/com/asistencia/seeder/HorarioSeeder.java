package com.asistencia.seeder;

import com.asistencia.models.Horario;
import com.asistencia.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
public class HorarioSeeder implements CommandLineRunner {
    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public void run(String... args) throws Exception {
        seedHorarios();
    }

    private void seedHorarios() {
//        if (horarioRepository.count() == 0) {
//            Horario horario = new Horario();
//            horario.setDia("Lunes");
//            horario.setHorarioInicio(LocalTime.parse("07:00"));
//            horario.setHorarioFin(LocalTime.parse("08:30"));
//            horarioRepository.save(horario);
//
//            horario = new Horario();
//            horario.setDia("Martes");
//            horario.setHorarioInicio(LocalTime.parse("07:00"));
//            horario.setHorarioFin(LocalTime.parse("09:15"));
//            horarioRepository.save(horario);
//
//            horario = new Horario();
//            horario.setDia("Miercoles");
//            horario.setHorarioInicio(LocalTime.parse("08:30"));
//            horario.setHorarioFin(LocalTime.parse("10:00"));
//            horarioRepository.save(horario);
//
//            horario = new Horario();
//            horario.setDia("Jueves");
//            horario.setHorarioInicio(LocalTime.parse("09:15"));
//            horario.setHorarioFin(LocalTime.parse("11:30"));
//            horarioRepository.save(horario);
//
//            horario = new Horario();
//            horario.setDia("Viernes");
//            horario.setHorarioInicio(LocalTime.parse("10:00"));
//            horario.setHorarioFin(LocalTime.parse("11:30"));
//            horarioRepository.save(horario);
//        }
    }
}
