package com.asistencia.seeder;

import com.asistencia.models.Aula;
import com.asistencia.models.Grupo;
import com.asistencia.repositories.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AulaSeeder implements CommandLineRunner {
    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public void run(String... args) throws Exception {
        seedAulas();
    }

    private void seedAulas() {
        if (aulaRepository.count() == 0) {
            Aula aula = new Aula();
            aula.setNombre("10");
            aula.setCapacidad(40);
            aula.setLatitud(-16.5098);
            aula.setLongitud(-68.1203);
            aulaRepository.save(aula);

            aula = new Aula();
            aula.setNombre("11");
            aula.setCapacidad(40);
            aula.setLatitud(-17.3996);
            aula.setLongitud(-66.1571);
            aulaRepository.save(aula);

            aula = new Aula();
            aula.setNombre("12");
            aula.setCapacidad(40);
            aula.setLatitud(-15.2341);
            aula.setLongitud(-69.4272);
            aulaRepository.save(aula);

            aula = new Aula();
            aula.setNombre("13");
            aula.setCapacidad(40);
            aula.setLatitud(-18.4767);
            aula.setLongitud(-70.3173);
            aulaRepository.save(aula);
        }
    }
}
