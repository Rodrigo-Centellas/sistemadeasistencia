package com.asistencia.services;

import com.asistencia.models.Clase;
import com.asistencia.repositories.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }

    public Optional<Clase> getClaseById(Long id) {
        return claseRepository.findById(id);
    }

    public Clase createClase(Clase clase) {
        return claseRepository.save(clase);
    }

    public Clase updateClase(Long id, Clase claseDetails) {
        Clase clase = claseRepository.findById(id).orElseThrow();
        clase.setCupo(claseDetails.getCupo());
        clase.setUser(claseDetails.getUser());
        clase.setGrupo(claseDetails.getGrupo());
        clase.setMateria(claseDetails.getMateria());
//        clase.setHorarios(claseDetails.getHorarios());
        return claseRepository.save(clase);
    }

    public void deleteClase(Long id) {
        claseRepository.deleteById(id);
    }
}
