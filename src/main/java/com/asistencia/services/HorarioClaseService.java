package com.asistencia.services;

import com.asistencia.models.HorarioClase;
import com.asistencia.repositories.HorarioClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioClaseService {

    @Autowired
    private HorarioClaseRepository horarioClaseRepository;

    public List<HorarioClase> findAll() {
        return horarioClaseRepository.findAll();
    }

    public HorarioClase save(HorarioClase horarioClase) {
        if (!isHorarioAvailable(horarioClase.getClase().getId(), horarioClase.getHorario().getId())) {
            throw new RuntimeException("Horario no disponible");
        }
        return horarioClaseRepository.save(horarioClase);
    }

    public void deleteById(Long id) {
        horarioClaseRepository.deleteById(id);
    }

    public boolean isHorarioAvailable(Long claseId, Long horarioId) {
        List<HorarioClase> existing = horarioClaseRepository.findByHorarioId(horarioId);
        for (HorarioClase hc : existing) {
            if (!hc.getClase().getId().equals(claseId)) {
                return false;
            }
        }
        return true;
    }
}
