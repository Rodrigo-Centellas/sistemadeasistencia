package com.asistencia.seeder;

import com.asistencia.models.Aula;
import com.asistencia.models.Modulo;
import com.asistencia.repositories.AulaRepository;
import com.asistencia.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ModuloSeeder implements CommandLineRunner {
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public void run(String... args) throws Exception {
        seedModulos();
    }

    private void seedModulos() {
        if (moduloRepository.count() == 0) {
            Modulo modulo1 = new Modulo();
            modulo1.setNombre("Modulo 236");
            moduloRepository.save(modulo1);

            Modulo modulo2 = new Modulo();
            modulo2.setNombre("Modulo 237");
            moduloRepository.save(modulo2);

            Modulo modulo3 = new Modulo();
            modulo3.setNombre("Modulo 238");
            moduloRepository.save(modulo3);

            Modulo modulo4 = new Modulo();
            modulo4.setNombre("Modulo 239");
            moduloRepository.save(modulo4);

            if (aulaRepository.count() == 0) {
                Aula aula = new Aula();
                aula.setNombre("10");
                aula.setCapacidad(40);
                aula.setModulo(modulo1);
                aulaRepository.save(aula);

                aula = new Aula();
                aula.setNombre("11");
                aula.setCapacidad(45);
                aula.setModulo(modulo2);
                aulaRepository.save(aula);

                aula = new Aula();
                aula.setNombre("12");
                aula.setCapacidad(30);
                aula.setModulo(modulo3);
                aulaRepository.save(aula);

                aula = new Aula();
                aula.setNombre("13");
                aula.setCapacidad(60);
                aula.setModulo(modulo4);
                aulaRepository.save(aula);
            }
        }
    }
}
