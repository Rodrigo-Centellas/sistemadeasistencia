package com.asistencia.services;

import com.asistencia.models.Horario;
import com.asistencia.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    public Horario updateHorario(Long id, Horario horario) {
        horario.setId(id);
        return horarioRepository.save(horario);
    }

    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
